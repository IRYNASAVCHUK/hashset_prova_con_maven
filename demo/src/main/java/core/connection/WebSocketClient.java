package core.connection;

import java.io.IOException;
import java.net.URI;
import java.util.logging.*;
import javax.websocket.*;

import core.logger.formatter.MyFormatter;
import core.utils.Constants;

@ClientEndpoint
public class WebSocketClient extends Handler {

    private static Session session;

    @OnOpen
    public void onOpen(Session session) {
        WebSocketClient.session = session;
        System.out.println("[CLIENT]: Connected to server with session ID: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("\n[CLIENT]: Received from server: \n" + message);
    }

    @Override
    public void publish(LogRecord record) {
        try {
            var logData = new MyFormatter().format(record);
            sendDataToServer(logData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendDataToServer(String jsonInputString) {
        try {
            if (session != null && session.isOpen()) {
                session.getBasicRemote().sendText(jsonInputString);
            } else {
                System.out.println("[CLIENT]: Session is not open.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void configureWebSocketClient(Logger logger) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://" + Constants.URL + ":" + Constants.PORT + "/ws/endpoint";
            System.out.println("[CLIENT]: Connecting to " + uri);
            container.connectToServer(WebSocketClient.class, URI.create(uri));
            WebSocketClient clientHandler = new WebSocketClient();
            clientHandler.setLevel(Level.ALL);
            clientHandler.setFormatter(new MyFormatter());
            for (Handler existingHandler : logger.getHandlers())
                logger.removeHandler(existingHandler);
            logger.addHandler(clientHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SecurityException {
    }

    @Override
    public void flush() {
    }
}
