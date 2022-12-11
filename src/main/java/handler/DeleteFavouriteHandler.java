package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import database.featuredVacancies.FeaturedVacanciesBase;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс обработки команды удаления избранной вакансии
 */
public class DeleteFavouriteHandler extends AbstractHandler {
    public DeleteFavouriteHandler(HeadHunterBot bot) {
        super(bot);
    }

    /**
     * Метод для  удаления вакансии из избранного
     * @param chatId        чат из телеграма
     * @param parsedCommand - спарсенная команда
     * @param update        - эвент из телеграмма
     * @throws JsonProcessingException
     */
    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {
        Integer userId = Math.toIntExact(update.getCallbackQuery().getMessage().getChat().getId());
        String vacancyId = parsedCommand.getText().trim();

        try {
            boolean isDeleted = new FeaturedVacanciesBase().delete(userId, vacancyId);

            if (isDeleted) {
                bot.sendQueue.add(new SendMessage(chatId, "Ваша вакансия успешно удалена."));
            } else {
                bot.sendQueue.add(new SendMessage(chatId, "Произошла ошибка при удалении вакансии, попробуйте заного."));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
