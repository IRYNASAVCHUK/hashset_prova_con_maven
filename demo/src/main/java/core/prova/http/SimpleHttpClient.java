package core.prova.http;

import java.io.*;
import java.net.*;
import java.util.logging.*;

import core.logger.formatter.MyFormatter;
import core.utils.Constants;


public class SimpleHttpClient extends SocketHandler {

    public SimpleHttpClient(String host, int port) throws IOException {
        super(host, port);
    }

    @Override
    public void publish(LogRecord record) {
        try {
            var logData = new MyFormatter().format(record);
            if (Constants.FORMAT_JSON)
                sendDataToServer(logData);
            else
                super.publish(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void configureHttpClient(Logger logger) {
        try {
            SimpleHttpClient httpClient = new SimpleHttpClient(Constants.URL, Constants.PORT);
            httpClient.setLevel(Level.ALL);
            httpClient.setFormatter(new MyFormatter());
            for (Handler existingHandler : logger.getHandlers())
                logger.removeHandler(existingHandler);
            logger.addHandler(httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendDataToServer(String jsonInputString) {
        try {
            URL url = new URL("http://" + Constants.URL + ":" + Constants.PORT + "/endpoint");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // opzionale
            int code = conn.getResponseCode();
            System.out.println("Response Code: " + code);
            // opzionale
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
