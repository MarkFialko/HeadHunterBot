package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import entity.FeaturedVacancies;
import entity.HeadHunterBot;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FavouriteHandler extends AbstractHandler {
    public FavouriteHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();

            FeaturedVacancies featuredVacancies = new FeaturedVacancies();

            String vacancyId = parsedCommand.getText().trim();
            Integer userId = Math.toIntExact(update.getCallbackQuery().getMessage().getChat().getId());

            Query query = session.createSQLQuery("SELECT * FROM featured_vacancies WHERE user_id = :id AND vacancy_id = :vacId");

            query.setParameter("id", userId);
            query.setParameter("vacId", vacancyId);

            if (query.getResultList().isEmpty()) {

                featuredVacancies.setVacancyId(vacancyId);
                featuredVacancies.setUserId(userId);

                session.save(featuredVacancies);
                session.getTransaction().commit();

                bot.sendQueue.add(new SendMessage(chatId, "Вакансия добавлена в избранное.\n"));
            } else {
                bot.sendQueue.add(new SendMessage(chatId, "Вы уже добавляли эту вакансию в избранное, чтобы посмотреть избранные вакансии,введите /favourites!\n"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
