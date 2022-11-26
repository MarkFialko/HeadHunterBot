package entity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Класс для работы с API HeadHunter
 */
public class API {
    final static String url = "https://api.hh.ru/";

    /**
     *
     * @param address - адрес, с которого брать данные
     * @return сообщение с ответом на запрос
     */
    public static String getResponse(String address) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + address))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
