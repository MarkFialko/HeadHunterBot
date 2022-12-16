package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import database.featuredVacancies.FeaturedVacancies;
import database.featuredVacancies.FeaturedVacanciesBase;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для добавления вакансии в избранное
 */
public class FavouriteHandler extends AbstractHandler {
    public FavouriteHandler(HeadHunterBot bot) {
        super(bot);
    }

    /**
     * Метод добавляет вакансию в избранное у конкретного пользователя
     * @param chatId        чат из телеграма
     * @param parsedCommand - спарсенная команда
     * @param update        - эвент из телеграмма
     * @throws JsonProcessingException
     */
    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {

        try {

            String vacancyId = parsedCommand.getText().trim();
            Integer userId = Math.toIntExact(update.getCallbackQuery().getMessage().getChat().getId());
            // Проверка на нахождение данной вакансии в избранном
            if (new FeaturedVacanciesBase().find(vacancyId,userId)) {
                FeaturedVacancies featuredVacancies = new FeaturedVacancies();
                featuredVacancies.setVacancyId(vacancyId);
                featuredVacancies.setUserId(userId);

                new FeaturedVacanciesBase().save(featuredVacancies);

                bot.sendQueue.add(new SendMessage(chatId, "Вакансия добавлена в избранное.\n"));
            } else {
                bot.sendQueue.add(new SendMessage(chatId, "Вы уже добавляли эту вакансию в избранное, чтобы посмотреть избранные вакансии,введите /favourites"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
