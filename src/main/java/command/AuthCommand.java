package command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class AuthCommand extends BotCommand {
    static ObjectMapper mapper = new ObjectMapper();

    public AuthCommand(String description, String messageForHelp) {
        super(description, "Команда позволяет авторизовать пользователя.");
    }

    public static String processResponse(String response) throws JsonProcessingException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);


        if (!response.equals("")) {
            return "Вы успешно авторизовались";
        }
        return "Ошибка";
    }
}
