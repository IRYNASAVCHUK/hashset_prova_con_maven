package core.prova.java_server;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import core.logger.MyFormatter;
import core.utils.Constants;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class WebSocketClientExample extends WebSocketClient {

    private static final Logger logger = Logger.getLogger(WebSocketClientExample.class.getName());

    public WebSocketClientExample(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("[CLIENT]: Connected to server");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("\n[CLIENT]: Received from server: \n" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("[CLIENT]: WebSocket connection closed");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("[CLIENT]: WebSocket error: " + ex.getMessage());
    }

    public static void main(String[] args) {
        try {
            String uri = "ws://" + Constants.URL + ":" + Constants.PORT + "/ws/endpoint";
            System.out.println("[CLIENT]: Connecting to " + uri);
            WebSocketClientExample client = new WebSocketClientExample(new URI(uri));
            client.connect();

            // Log messages will be sent to the WebSocket server
            WebSocketClientLogger clientLogger = new WebSocketClientLogger(client);
            Logger.getLogger("").addHandler(clientLogger);
            logger.info("This is a test log message sent through the WebSocket connection.");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static class WebSocketClientLogger extends Handler {
        private final WebSocketClient webSocketClient;

        public WebSocketClientLogger(WebSocketClient webSocketClient) {
            this.webSocketClient = webSocketClient;
            this.setLevel(Level.ALL);
            this.setFormatter(new MyFormatter());
        }

        @Override
        public void publish(LogRecord record) {
            if (webSocketClient.isOpen()) {
                var logData = new MyFormatter().format(record);
                webSocketClient.send(logData);
            } else {
                System.out.println("[CLIENT]: WebSocket connection is not open.");
            }
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() throws SecurityException {
        }
    }
}