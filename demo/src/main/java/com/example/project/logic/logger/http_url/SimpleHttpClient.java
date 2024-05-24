package com.example.project.logic.logger.http_url;

import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

import com.example.project.logic.logger.MyFormatter;
import com.example.project.logic.utils.Constants;

public class SimpleHttpClient extends SocketHandler {

    public SimpleHttpClient(String host, int port) throws IOException {
        super(host, port);
    }

    @Override
    public void publish(LogRecord record) {
        try {
            String logData = new MyFormatter().format(record);
            if (Constants.FORMAT_JSON) {
                sendDataToServer(logData); // Invia i dati al server HTTP
            } else {
                super.publish(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static void configureHttpClient(Logger logger) {
        try {
            SimpleHttpClient httpClient = new SimpleHttpClient(Constants.URL, Constants.PORT);
            
            // Configurazione del livello di logging e del formatter
            httpClient.setLevel(Level.ALL);
            httpClient.setFormatter(new MyFormatter());
            
            // Rimozione di tutti gli handler esistenti dal logger
            for (Handler existingHandler : logger.getHandlers()) {
                logger.removeHandler(existingHandler);
            }
            
            // Aggiunta di SimpleHttpClient come handler per il logger
            logger.addHandler(httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendDataToServer(String jsonInputString) {
        try {
            URL url = new URL("http://"+Constants.URL + ":" + Constants.PORT + "/endpoint"); // URL del server
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = conn.getResponseCode();
            System.out.println("Response Code: " + code);

            // Optional: Leggi la risposta
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
