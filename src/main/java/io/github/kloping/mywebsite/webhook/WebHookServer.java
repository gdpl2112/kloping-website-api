package io.github.kloping.mywebsite.webhook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import io.github.kloping.io.ReadUtils;
import io.github.kloping.mywebsite.broadcast.WebHookBroadcast;
import io.github.kloping.mywebsite.webhook.e0.OrderReq;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author github.kloping
 */
public class WebHookServer {
    public static final Integer PORT = 10516;
    public static final String URL = "/";

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext(URL, WebHookServer::handle);
        server.start();
        System.out.println("webhook server start ok");
    }

    public static void handle(HttpExchange exchange) throws IOException {
        String body = ReadUtils.readAll(exchange.getRequestBody(), "utf-8");
        try {
            JSONObject jo = JSON.parseObject(body);
            WebHookBroadcast.INSTANCE.broadcast(jo.toJavaObject(OrderReq.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        handleSuccessResponse(exchange, "{\"ec\":200,\"em\":\"\"}");
    }

    private static void handleSuccessResponse(HttpExchange exchange, String responseBody) {
        try (OutputStream outputStream = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, responseBody.getBytes(StandardCharsets.UTF_8).length);
            outputStream.write(responseBody.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
