package com.example.project.logic.websocket;

import com.example.project.logic.utils.Constants;

import java.util.Scanner;
import org.glassfish.tyrus.server.Server;

public class WebSocketServerMain {
    public static void main(String[] args) {
        Server server = new Server(Constants.URL, Constants.PORT, "/ws", WebSocketServerEndpoint.class);
        try {
            server.start();
            System.out.println("[SERVER]: Server started at ws://"
                    + Constants.URL + ":" + Constants.PORT + "/ws/endpoint");
            System.out.println("[SERVER]: Press 'q' to stop the server...");
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
