package server;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Класс сервера для обработки GET запросов с кодом для авторизации
 */
public class HttpNanoServer extends NanoHTTPD {
    private final Map<String, String> queryParams = Collections.synchronizedMap(new WeakHashMap<>());

    public HttpNanoServer() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    /**
     * Переопределяем метод serve, нужен для передеачи GET параметров при авторизации
     *
     * @param session
     * @return Ответ с сообщением на страницу
     */
    @Override
    public Response serve(IHTTPSession session) {

        switch (session.getMethod()) {
            case GET -> {

                String params = session.getQueryParameterString();

                if (params != null) {
                    for (String param : params.split("&")) {
                        queryParams.put(param.split("=")[0], param.split("=")[1]);
                    }
                }

            }
        }
        return newFixedLengthResponse("");
    }

    /**
     * Метод для получения параметров
     * @return параметры
     */
    public Map<String, String> getQueryParams() {
        return queryParams;
    }

}
