package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import entity.HeadHunterBot;
import entity.Vacancy;
import keyboard.KeyboardCreator;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;

public class ShowFavouriteHandler extends AbstractHandler {
    public ShowFavouriteHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {

        Integer userId = Math.toIntExact(update.getMessage().getChat().getId());

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();

            Query query = session.createSQLQuery("SELECT * FROM featured_vacancies WHERE user_id = :id");
            query.setParameter("id", userId);

            List<Object[]> vacancies = query.getResultList();

            if (query.getResultList().isEmpty()) {
                bot.sendQueue.add(new SendMessage(chatId, "У Вас нет избранных вакансий.\n"));
            } else {

                for (Object[] vacancy : vacancies) {
                    String vacancyId = vacancy[1].toString();

                    bot.sendQueue.add(getFavouritesVacancy(vacancyId, chatId));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private SendMessage getFavouritesVacancy(String idVacancy, String chatId) throws JsonProcessingException {
        SendMessage message = new SendMessage();

        String text = "Избранные вакансии:\n\n";

        HashMap<String, String> deleteKeyboard = new HashMap<>();

        deleteKeyboard.put("Удалить из избранного", "/deleteFavourite " + idVacancy);

        message.setChatId(chatId);

        message.setText(text + Vacancy.getVacancy(idVacancy).toString());


        message.setReplyMarkup(KeyboardCreator.getInlineKeyboard(1, deleteKeyboard));

        return message;
    }

}
