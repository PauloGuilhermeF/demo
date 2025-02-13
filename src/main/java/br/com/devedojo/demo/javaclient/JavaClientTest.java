package br.com.devedojo.demo.javaclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Base64;

public class JavaClientTest {
    public static void main(String[] args) {
        String user = "paulo";
        String password = "123456789";
        HttpURLConnection connection = null;

        try {
            URI uri = new URI("http://localhost:8080/v1/protected/students/1");
            URL url = uri.toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "Basic " + encodeUsernamePassword(user, password));

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder jsonSB = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonSB.append(line);
                    }
                    System.out.println(jsonSB.toString());
                }
            } else {
                System.out.println("Erro na requisição: " + responseCode);
            }
            System.out.println("URL criada com sucesso: " + url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String encodeUsernamePassword(String user, String password) {
        String userPassword = user + ":" + password;
        return Base64.getEncoder().encodeToString(userPassword.getBytes());
    }
}