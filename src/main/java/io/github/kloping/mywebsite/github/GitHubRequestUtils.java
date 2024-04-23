package io.github.kloping.mywebsite.github;

import com.alibaba.fastjson.JSON;
import io.github.kloping.mywebsite.github.api.AccessTokenDTO;
import io.github.kloping.mywebsite.github.api.GithubUser;
import io.github.kloping.mywebsite.utils.BlogCodeUtils;
import io.github.kloping.mywebsite.utils.SSLSocketClientUtil;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author HRS-Computer
 */
@Component
public class GitHubRequestUtils {
    public static final String ACCESS_URL = "https://github.com/login/oauth/access_token";

    @Value("${proxy.url}")
    public String url;

    @Value("${proxy.port}")
    public Integer port;

    @Value("${proxy.auth_url}")
    String auth_url;

    public Connection getConnection() {
        Connection connection = new HttpConnection();
        connection.ignoreHttpErrors(true).ignoreContentType(true);
        if (port == null || url == null) return connection;
        else if (BlogCodeUtils.testProxyViability(url, port, auth_url))
            connection.proxy(url, port).sslSocketFactory(SSLSocketClientUtil.getSocketFactory(SSLSocketClientUtil.getX509TrustManager()));
        return connection;
    }

    public String getAccessToken(AccessTokenDTO dto) {
        Document doc = null;
        try {
            doc = getConnection().url(ACCESS_URL)
                    .header("accept", "application/json")
                    .data("client_id", dto.getClient_id())
                    .data("client_secret", dto.getClient_secret())
                    .data("code", dto.getCode())
                    .data("redirect_uri", dto.getRedirect_uri())
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            return doc.body().text();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        Connection con = getConnection().url("https://api.github.com/user?access_token=" + accessToken)
                .header("accept", "application/json")
                .header("Authorization", "token " + accessToken);
        try {
            Document doc = con.get();
            String data = doc.body().text();
            GithubUser githubUser = JSON.parseObject(data, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

