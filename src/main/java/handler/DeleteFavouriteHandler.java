package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import entity.HeadHunterBot;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DeleteFavouriteHandler extends AbstractHandler {
    public DeleteFavouriteHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {
        Integer userId = Math.toIntExact(update.getCallbackQuery().getMessage().getChat().getId());
        String vacancyId = parsedCommand.getText().trim();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();

            Query query = session.createSQLQuery("DELETE FROM featured_vacancies WHERE user_id = :id and vacancy_id = :vacId");
            query.setParameter("id", userId);
            query.setParameter("vacId", vacancyId);

            Integer result = query.executeUpdate();

            session.getTransaction().commit();

            if (result == 1) {
                bot.sendQueue.add(new SendMessage(chatId, "Ваша вакансия успешно удалена."));
            } else {
                bot.sendQueue.add(new SendMessage(chatId,"Произошла ошибка при удалении вакансии, попробуйте заного."));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
