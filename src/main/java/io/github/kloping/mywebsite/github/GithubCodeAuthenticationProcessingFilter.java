package io.github.kloping.mywebsite.github;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.github.api.AccessTokenDTO;
import io.github.kloping.mywebsite.github.api.GithubUser;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.github.kloping.mywebsite.github.GithubCodeAuthenticationProvider.*;

public class GithubCodeAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/github/code", "GET");

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private GitHubRequestUtils utils;

    public GithubCodeAuthenticationProcessingFilter(String clientId, String clientSecret, String redirectUri, GitHubRequestUtils utils) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.utils = utils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("GET")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String code = obtainCode(request);
        code = (code != null) ? code : "";
        code = code.trim();
        String state = obtainState(request);
        state = (state != null) ? state : "";

        GithubCodeAuthenticationToken atoken = null;

        try {
            AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
            accessTokenDTO.setClient_id(clientId);
            accessTokenDTO.setClient_secret(clientSecret);
            accessTokenDTO.setCode(code);
            accessTokenDTO.setRedirect_uri(redirectUri);
            accessTokenDTO.setState(state);
            String result = utils.getAccessToken(accessTokenDTO);
            if (result == null) {
                response.sendRedirect("/error.html?tips=An exception occurred while connecting to the github server, please try again later");
                return null;
            }
            System.out.println(result);
            JSONObject jo = JSON.parseObject(result);
            String access_token = jo.getString("access_token");
            if (Judge.isEmpty(access_token)) {
                response.sendRedirect("/error.html?tips=Exception while parsing token, please try again later");
                return null;
            }
            GithubUser user = utils.getUser(access_token);
            if (Judge.isEmpty(user.getLogin())) {
                response.sendRedirect("/error.html?tips=An exception occurred while getting github user information, please try again later");
                return null;
            }
            EID2TOKEN.put(user.getLogin(), access_token);
            atoken = new GithubCodeAuthenticationToken(code, access_token);
            atoken.setGithubUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDetails(request, atoken);
        return this.getAuthenticationManager().authenticate(atoken);
    }

    private String obtainCode(HttpServletRequest request) {
        return request.getParameter(FORM_CODE_KEY);
    }

    private String obtainState(HttpServletRequest request) {
        return request.getParameter(FORM_STATE_KEY);
    }

    protected void setDetails(HttpServletRequest request, GithubCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
