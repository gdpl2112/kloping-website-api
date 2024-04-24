package io.github.kloping.mywebsite.github;

import com.alibaba.fastjson.JSON;
import io.github.kloping.mywebsite.domain.po.UserTemp;
import io.github.kloping.mywebsite.github.api.GithubUser;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static io.github.kloping.mywebsite.config.UserDetailsServiceImpl.GITHUB_TYPE;

public class GithubCodeAuthenticationProvider implements AuthenticationProvider {
    public static final Map<String, String> EID2TOKEN = new HashMap<>();

    public static final String FORM_CODE_KEY = "code";
    public static final String FORM_STATE_KEY = "state";
    public static final String SESSION_CODE_KEY = FORM_CODE_KEY;
    public static final String SESSION_STATE_KEY = FORM_STATE_KEY;

    private UserDetailsService userDetailsService;
    private UserTempMapper userTempMapper;

    public GithubCodeAuthenticationProvider(UserDetailsService userDetailsService, UserTempMapper userTempMapper) {
        this.userDetailsService = userDetailsService;
        this.userTempMapper = userTempMapper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        authenticationChecks(authentication);
        GithubCodeAuthenticationToken githubCodeAuthenticationToken = (GithubCodeAuthenticationToken) authentication;
        GithubUser githubUser = githubCodeAuthenticationToken.getGithubUser();
        UserDetails userDetails = userDetailsService.loadUserByUsername(githubUser.getLogin());
        GithubCodeAuthenticationToken result = new GithubCodeAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        result.setDetails(githubCodeAuthenticationToken.getDetails());
        return result;
    }

    /**
     * 认证信息校验
     *
     * @param authentication
     */
    private void authenticationChecks(Authentication authentication) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        GithubCodeAuthenticationToken githubCodeAuthenticationToken = (GithubCodeAuthenticationToken) authentication;
        GithubUser user = githubCodeAuthenticationToken.getGithubUser();
        String name = user.getLogin();
        String eid = user.getLogin();
        eid = eid == null ? "" : eid;
        UserTemp userTemp = userTempMapper.selectById(name);
        if (userTemp == null) {
            userTemp = new UserTemp().setEid(eid).setQid(0L).setNickname(user.getLogin())
                    .setPwd(authentication.getCredentials().toString()).setAuth("")
                    .setRegt(System.currentTimeMillis()).setType(GITHUB_TYPE)
                    .setIcon(user.getAvatar_url()).setAnnex(JSON.toJSONString(user));
            userTempMapper.insert(userTemp);
        } else {
            userTemp.setIcon(user.getAvatar_url()).setAnnex(JSON.toJSONString(user));
            user.setAvatar_url(JSON.toJSONString(user));
            userTempMapper.updateById(userTemp);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (GithubCodeAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public static boolean isEmail(String email) {
        if (email == null || email.isEmpty() || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }
}
