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
        HttpServer server = HttpServer.create(new InetSocketAddress(Constants.PORT), 0);
        server.createContext("/endpoint", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server is listening on port\t"+ Constants.PORT);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                // Read the request body
                byte[] requestBodyBytes = exchange.getRequestBody().readAllBytes();
                String requestBody = new String(requestBodyBytes, StandardCharsets.UTF_8);
                System.out.println("Received: " + requestBody);

                // Send a response
                String response = "Data received";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            }
        }
    }
}
