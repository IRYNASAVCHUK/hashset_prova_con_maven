package core.prova.http;

import com.sun.net.httpserver.*;

import core.utils.Constants;

import java.io.*;
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
