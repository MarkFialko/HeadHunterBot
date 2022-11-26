package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import entity.HeadHunterBot;
import entity.Vacancy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки команды с вакансиями
 */
public class VacanciesHandler extends AbstractHandler {

    public VacanciesHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {

        bot.sendQueue.add(collectVacancies(chatId));
    }

    /**
     *
     * @param chatId чат телеграма
     * @return сообщение с вакансияи телеграм бота
     * @throws JsonProcessingException
     */
    private SendMessage collectVacancies(String chatId) throws JsonProcessingException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        StringBuilder vacancies = new StringBuilder();

        for (Vacancy vacancy : Vacancy.getVacancies()) {
            vacancies.append(vacancy.getInfo()).append("\n");
        }

        message.setText(vacancies.toString());

        return message;
    }
}
