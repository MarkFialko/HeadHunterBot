package service;


import auth.Auth;
import com.github.scribejava.core.model.OAuth2AccessToken;
import entity.HeadHunterBot;
import entity.Users;
import handler.HibernateUtil;
import org.hibernate.Session;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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

    @Override
    public void run() {

        while (isActive) {
            if (bot.getServer().getQueryParams().containsKey("code")) {
                String code = bot.getServer().getQueryParams().get("code");
                Integer telegramId = Math.toIntExact(update.getMessage().getChat().getId());
                try {
                    OAuth2AccessToken accessToken = Auth.getAccessToken(code);

                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session.getTransaction().begin();

                    Users user = new Users();

                    user.setAccessToken(accessToken.getAccessToken());
                    user.setTelegramId(telegramId);

                    session.save(user);
                    session.getTransaction().commit();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void disabled() {
        isActive = false;
    }


}
