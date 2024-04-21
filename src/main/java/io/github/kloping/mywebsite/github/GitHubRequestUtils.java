package io.github.kloping.mywebsite.github;

import com.alibaba.fastjson.JSON;
import io.github.kloping.mywebsite.github.api.AccessTokenDTO;
import io.github.kloping.mywebsite.github.api.GithubUser;
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
    @Value("${proxy.url}")
    public String url;

    @Value("${proxy.port}")
    public Integer port;

    private boolean k = false;
    private int i = 2;

    private Connection getConnection() {
        Connection connection = new HttpConnection();
        connection.ignoreHttpErrors(true).ignoreContentType(true);
        if (port == null || url == null) return connection;
        else {
            if (k) {
                connection.proxy(url, port).sslSocketFactory(SSLSocketClientUtil.getSocketFactory(SSLSocketClientUtil.getX509TrustManager()));
                i--;
                if (i == 0) k = false;
            } else {
                i++;
                if (i == 2) k = true;
            }
        }
        return connection;
    }

    public static final String ACCESS_URL = "https://github.com/login/oauth/access_token";

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
        } catch (java.net.SocketTimeoutException e) {
            System.err.println(e.getMessage());
        } catch (java.net.ConnectException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            String result = doc.body().text();
            return result;
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
            return null;
        }
    }
}

