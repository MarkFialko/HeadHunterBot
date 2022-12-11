package service;


import auth.Auth;
import com.github.scribejava.core.model.OAuth2AccessToken;
import database.user.UserBase;
import entity.HeadHunterBot;
import database.user.Users;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Класс потока для проверки кода авторизации у пользователя
 */
public class AuthChecker implements Runnable {
    Boolean isActive = true;
    HeadHunterBot bot;
    String chatId;
    Update update;

    public AuthChecker(Update update, String chatId, HeadHunterBot bot) {
        this.bot = bot;
        this.chatId = chatId;
        this.update = update;
    }

    /**
     * Проверяет наличие кода для авотризации, пока он не прийдет с сервера
     */
    @Override
    public void run() {

        while (isActive) {
            if (bot.getServer().getQueryParams().containsKey("code")) {
                String code = bot.getServer().getQueryParams().get("code");
                Integer telegramId = Math.toIntExact(update.getMessage().getChat().getId());
                try {
                    OAuth2AccessToken accessToken = Auth.getAccessToken(code);

                    if (new UserBase().findByTelegramId(telegramId) == null) {
                        Users newUser = new Users();
                        newUser.setTelegramId(telegramId);
                        newUser.setAccessToken(accessToken.getAccessToken());
                        new UserBase().save(newUser);
                    } else {
                        Users oldUser = new Users();
                        oldUser.setTelegramId(telegramId);
                        oldUser.setAccessToken(accessToken.getAccessToken());
                        new UserBase().update(oldUser);
                    }

                    disabled();

                } catch (IOException | ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Останавливает поиск кода после его нахождения
     */
    public void disabled() {
        isActive = false;
    }


}
