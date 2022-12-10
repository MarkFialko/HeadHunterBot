package auth;

import api.HHApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Auth {
    private static final String clientId = System.getenv("CLIENT_ID");
    private static final String clientSecret = System.getenv("CLIENT_SECRET");
    private static final OAuth20Service service = new ServiceBuilder(clientId)
            .apiSecret(clientSecret)
            .callback("http://localhost:8080")
            .build(HHApi.instance());

    private static final String PROTECTED_RESOURCE_URL = "https://api.hh.ru/me";

    @SuppressWarnings("PMD.SystemPrintln")
    public static String getAuthorizationUrl() {
        return service.getAuthorizationUrl();
    }

    public static String getResponse(String accessToken) throws IOException, ExecutionException, InterruptedException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);
        try (Response response = service.execute(request)) {
            if (response.getCode() == 200) {
                return response.getBody();
            }
        }
        return "Ошибка при авторизации";
    }


    public static OAuth2AccessToken getAccessToken(String code) throws IOException, ExecutionException, InterruptedException {
        final OAuth2AccessToken accessToken = service.getAccessToken(code);

        return accessToken;

    }


}