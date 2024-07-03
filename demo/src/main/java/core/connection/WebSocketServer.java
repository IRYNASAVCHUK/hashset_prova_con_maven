package core.connection;

import java.io.IOException;
import java.util.Scanner;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.server.Server;

import core.utils.Constants;

@ServerEndpoint("/endpoint")
public class WebSocketServer {
    public static void main(String[] args) {
        Server server = new Server(Constants.URL, Constants.PORT, "/ws", WebSocketServer.class);
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
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[SERVER]: Connection established with session ID: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("\n[SERVER]: Received: \n" + message);
        //String response = "Data received";
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("[SERVER]: Session " + session.getId() + " closed.");
    }
}
