package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.HelpCommand;
import command.ParsedCommand;
import command.VacanciesCommand;
import filter.Filter;
import filter.FilterData;
import entity.HeadHunterBot;
import entity.Vacancy;
import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.HashMap;
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
        final boolean isValid = text != FilterData.FILTER_ERROR.getValue();

        InlineKeyboardMarkup keyboard = isValid
                ? vacanciesCommand.selectKeyboard(parsedCommand)
                : new HelpCommand().setKeyboard(false);


        if (!parsedCommand.getText().contains("get") && isValid) {
            vacancyFilter.setFilters(vacanciesCommand.getFilterData(parsedCommand));
        }

        if (Objects.equals(text, FilterData.FILTER_GET.getValue())) {
            for (Vacancy vacancy : Vacancy.getVacancies(vacancyFilter.getStringFilters())) {
                bot.sendQueue.add(prepareVacancy(chatId,vacancy));
            }
            vacancyFilter.removeFilter();
        } else {
            bot.sendQueue.add(getVacancyFilters(chatId, keyboard, text));
        }
    }


    /**
     * @param chatId   чат телеграма
     * @param keyboard клавиатура телеграма
     * @param text     текст с сообщением для отправки
     * @return
     * @throws JsonProcessingException
     */
    private SendMessage getVacancyFilters(String chatId, InlineKeyboardMarkup keyboard, String text) throws JsonProcessingException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        message.setText(text);
        message.setReplyMarkup(keyboard);

        return message;
    }


    private SendMessage prepareVacancy(String chatId, Vacancy vacancy) {

        HashMap<String,String> addFavouriteVacancy = new HashMap<>();

        addFavouriteVacancy.put("Добавить в избранное","/addToFavourite " + vacancy.getId());

        SendMessage vacancyMessage = new SendMessage();

        vacancyMessage.setChatId(chatId);
        vacancyMessage.setText(vacancy.toString() + "\n\n");

        vacancyMessage.setReplyMarkup(KeyboardCreator.getInlineKeyboard(1,addFavouriteVacancy));

        return vacancyMessage;

    }

}
