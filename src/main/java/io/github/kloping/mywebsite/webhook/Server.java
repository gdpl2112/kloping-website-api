package io.github.kloping.mywebsite.webhook;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import io.github.kloping.io.ReadUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author github.kloping
 */
public class Server {
    public static final Integer PORT = 10516;
    public static final String URL = "/";

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext(URL, Server::handle);
        server.start();
        System.out.println("webhook server start ok");
    }

    public static void handle(HttpExchange exchange) throws IOException {
        String body = ReadUtils.readAll(exchange.getRequestBody(), "utf-8");
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
