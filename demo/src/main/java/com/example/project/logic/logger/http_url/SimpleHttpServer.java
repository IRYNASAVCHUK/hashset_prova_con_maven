package com.example.project.logic.logger.http_url;

import com.example.project.logic.utils.Constants;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(Constants.PORT), 0);
        server.createContext("/endpoint", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                byte[] requestBodyBytes = exchange.getRequestBody().readAllBytes();
                var requestBody = new String(requestBodyBytes, StandardCharsets.UTF_8);
                System.out.println("Received: " + requestBody);
                var response = "Data received";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }
}
