package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import database.featuredVacancies.FeaturedVacancies;
import database.featuredVacancies.FeaturedVacanciesBase;
import entity.HeadHunterBot;
import entity.Vacancy;
import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;

/**
 * Хендлер на показ израбнных вакансий
 */
public class ShowFavouriteHandler extends AbstractHandler {
    public ShowFavouriteHandler(HeadHunterBot bot) {
        super(bot);
    }

    /**
     * Метод показывает список избранных вакансий у пользователя
     * @param chatId        чат из телеграма
     * @param parsedCommand - спарсенная команда
     * @param update        - эвент из телеграмма
     * @throws JsonProcessingException
     */
    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {
        Integer userId = Math.toIntExact(update.getMessage().getChat().getId());

        try {
            List<FeaturedVacancies> result = new FeaturedVacanciesBase().show(userId);

            if (result.isEmpty()) {
                bot.sendQueue.add(new SendMessage(chatId, "У Вас нет избранных вакансий.\n"));
            } else {
                bot.sendQueue.add(new SendMessage(chatId, "Избранные вакансии:\n\n"));

                for (FeaturedVacancies vacancy : result) {
                    bot.sendQueue.add(getFavouritesVacancy(vacancy.getVacancyId(), chatId));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод Получает информацию о вакансии
     * @param idVacancy идентификатор ваакансии
     * @param chatId идентифиактор чата телеграмм
     * @return
     * @throws JsonProcessingException
     */
    private SendMessage getFavouritesVacancy(String idVacancy, String chatId) throws JsonProcessingException {
        SendMessage message = new SendMessage();

        HashMap<String, String> deleteKeyboard = new HashMap<>();

        deleteKeyboard.put("Удалить из избранного", "/deleteFavourite " + idVacancy);

        message.setChatId(chatId);
        message.setText(Vacancy.getVacancy(idVacancy).toString());

        message.setReplyMarkup(KeyboardCreator.getInlineKeyboard(1, deleteKeyboard));

        return message;
    }

}
