package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import command.VacanciesCommand;
import filter.Filter;
import filter.FilterData;
import entity.HeadHunterBot;
import entity.Vacancy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Objects;

/**
 * Класс для обработки команды с вакансиями
 */
public class VacanciesHandler extends AbstractHandler {

    Filter vacancyFilter = new Filter();
    VacanciesCommand vacanciesCommand = new VacanciesCommand();

    public VacanciesHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {

        String text = vacanciesCommand.selectMessage(parsedCommand);
        InlineKeyboardMarkup keyboard = vacanciesCommand.selectKeyboard(parsedCommand);

        if (!parsedCommand.getText().contains("get")) {
            vacancyFilter.setFilters(vacanciesCommand.getFilterData(parsedCommand));
        }

        bot.sendQueue.add(collectVacancies(chatId, keyboard, text));
    }

    @Override
    public AbstractHandler setBot(HeadHunterBot bot) {
        this.bot = bot;
        return this;
    }

    /**
     *
     * @param chatId чат телеграма
     * @param keyboard клавиатура телеграма
     * @param text текст с сообщением для отправки
     * @return
     * @throws JsonProcessingException
     */
    private SendMessage collectVacancies(String chatId, InlineKeyboardMarkup keyboard, String text) throws JsonProcessingException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        if (Objects.equals(text, FilterData.FILTER_GET.getValue())) {
            StringBuilder textBuilder = new StringBuilder(text);
            for (Vacancy vacancy : Vacancy.getVacancies(vacancyFilter.getStringFilters())) {
                textBuilder.append(vacancy.getInfo()).append("\n\n");
            }
            text = textBuilder.toString();
            vacancyFilter.removeFilter();
        }

        message.setText(text);
        message.setReplyMarkup(keyboard);

        return message;
    }
}
