package core.logger.handler;

import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/endpoint")
public class WebSocketServer {    
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
