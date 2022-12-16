package handler;

import auth.Auth;
import auth.AuthInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import command.ParsedCommand;
import database.user.UserBase;
import database.user.Users;
import entity.HeadHunterBot;
import entity.UserHH;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * Класс для обработки команды пользователя
 */
public class UserHandler extends AbstractHandler {
    public UserHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws IOException, ExecutionException, InterruptedException {

        Integer telegramId = Math.toIntExact(update.getMessage().getChat().getId());
        Users user = new UserBase().findByTelegramId(telegramId);
        if (user != null) {
            String accessToken = user.getAccessToken();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

            String response = Auth.getResponse(accessToken);

            if (!Objects.equals(response, AuthInfo.AUTH_ERROR_MESSAGE.toString())) {
                UserHH newUser = mapper.readValue(response, UserHH.class);
                bot.sendQueue.add(getUserInfo(chatId, newUser.toString()));
            } else {
                bot.sendQueue.add(new SendMessage(chatId, AuthInfo.OLD_USER_FOR_AUTH_MESSAGE.toString()));
            }
        } else {
            bot.sendQueue.add(new SendMessage(chatId, AuthInfo.NEW_USER_FOR_AUTH_MESSAGE.toString()));
        }

    }

    /**
     * Получить информацию о пользователе
     *
     * @param chatId чат телеграм
     * @return сообщение для телеграм бота
     */
    private SendMessage getUserInfo(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        return message;
    }
}
