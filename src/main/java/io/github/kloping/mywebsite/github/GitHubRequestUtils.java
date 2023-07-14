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

@Component
public class GitHubRequestUtils {
    @Value("${proxy.url}")
    String url;

    @Value("${proxy.port}")
    Integer port;

    private Connection connection;

    {
        connection = new HttpConnection();
        connection.ignoreHttpErrors(true).ignoreContentType(true);
        connection.proxy(url, port)
                .sslSocketFactory(SSLSocketClientUtil.getSocketFactory(SSLSocketClientUtil.getX509TrustManager()));
    }

    public String getAccessToken(AccessTokenDTO dto) {
        Document doc = null;
        try {
            doc = connection.url("https://github.com/login/oauth/access_token")
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
        connection.url("https://api.github.com/user?access_token=" + accessToken)
                .header("accept", "application/json")
                .header("Authorization", "token " + accessToken);
        try {
            Document doc = connection.get();
            String data = doc.body().text();
            GithubUser githubUser = JSON.parseObject(data, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            return null;
        }
    }
}

