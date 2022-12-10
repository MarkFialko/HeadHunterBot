package handler;

import api.API;
import auth.Auth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import command.ParsedCommand;
import entity.HeadHunterBot;
import entity.UserHH;
import entity.Vacancy;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.List;
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

            Session session = HibernateUtil.getSessionFactory().openSession();

            session.getTransaction().begin();

            Query query = session.createSQLQuery("SELECT * FROM users WHERE telegram_id = :id");
            query.setParameter("id",telegramId);

            List<Object[]> response  = query.getResultList();

            String accessToken = response.get(0)[1].toString();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);



            String re = Auth.getResponse(accessToken);

            UserHH newUser =  mapper.readValue(re, UserHH.class);

            String text = newUser.getEmail();

        bot.sendQueue.add(getUserInfo(chatId, update,text));

    }



    /**
     * Получить информацию о пользователе
     * @param chatId чат телеграм
     * @param update эвент телеграм
     * @return сообщение для телеграм бота
     */
    private SendMessage getUserInfo(String chatId, Update update,String text) {
        SendMessage message = new SendMessage();

        message.setChatId(chatId);


        message.setText(text);

        return message;
    }
}
