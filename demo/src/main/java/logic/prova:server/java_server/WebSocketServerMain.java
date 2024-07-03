package logic.java_server;


import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import logic.utils.Constants;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class WebSocketServerMain {
    public static void main(String[] args) throws InterruptedException {
        WebSocketServer server = new WebSocketServer(new InetSocketAddress(Constants.URL, Constants.PORT)) {
            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                System.out.println("[SERVER]: Connection established with session ID: " + conn.getRemoteSocketAddress());
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                System.out.println("[SERVER]: Session " + conn.getRemoteSocketAddress() + " closed.");
            }

            @Override
            public void onMessage(WebSocket conn, String message) {
                System.out.println("\n[SERVER]: Received: \n" + message);
                conn.send(message);
            }

            @Override
            public void onError(WebSocket conn, Exception ex) {
                System.out.println("[SERVER]: WebSocket error: " + ex.getMessage());
            }

            @Override
            public void onStart() {
                System.out.println("[SERVER]: Server started at ws://" + Constants.URL + ":" + Constants.PORT + "/ws/endpoint");
                System.out.println("[SERVER]: Press 'q' to stop the server...");
            }
        };

        try {
            server.start();
            try (Scanner scanner = new Scanner(System.in)) {
                while (!scanner.nextLine().equalsIgnoreCase("q")) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
            System.out.println("[SERVER]: Server stopped.");
        }
    }
}