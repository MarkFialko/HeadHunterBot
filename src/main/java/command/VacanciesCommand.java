package command;

import entity.FilterData;
import entity.FilterKey;
import javafx.util.Pair;
import keyboard.Keyboard;
import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Locale;

/**
 * Класс с описанием команды Вакансии
 */
public class VacanciesCommand extends BotCommand {
    public VacanciesCommand() {
        super("", CommandInfo.VACANCIES_HELPMESSAGE.getValue());
    }

    /**
     *
     * @param parsedCommand спарсенная команда
     * @return сообщение для конкретного фильтра вакансий
     */
    public String selectMessage(ParsedCommand parsedCommand) {
        if (parsedCommand.getText().length() == 0) {
            return FilterData.FILTER_REGION.getValue();
        }

        FilterKey filterQuery = getFilterInfo(parsedCommand);

        switch (filterQuery) {
            case AREA -> {
                return FilterData.FILTER_AREA.getValue();
            }
            case SPECIALIZATION -> {
                return FilterData.FILTER_SPECIALIZATION.getValue();
            }
            case SCHEDULE -> {
                return FilterData.FILTER_SCHEDULE.getValue();
            }
            case GET -> {
                return FilterData.FILTER_GET.getValue();
            }
        }

        return FilterData.FILTER_ERROR.getValue();
    }

    /**
     *
     * @param parsedCommand спарсенная команда
     * @return Клавиатура для конкретного фильтра вакансий
     */
    public InlineKeyboardMarkup selectKeyboard(ParsedCommand parsedCommand) {
        if (parsedCommand.getText().length() == 0) {
            return KeyboardCreator.getInlineKeyboard(2, Keyboard.VACANCIES_AREA_KEYBOARD.getButtons());
        }

        FilterKey filterQuery = getFilterInfo(parsedCommand);

        switch (filterQuery) {
            case AREA -> {
                return KeyboardCreator.getInlineKeyboard(2, Keyboard.VACANCIES_PROFESSION_KEYBOARD.getButtons());
            }
            case SPECIALIZATION -> {
                return KeyboardCreator.getInlineKeyboard(2, Keyboard.VACANCIES_SCHEDULE_KEYBOARD.getButtons());
            }
            case SCHEDULE -> {
                return KeyboardCreator.getInlineKeyboard(2, Keyboard.VACANCIES_GET_KEYBOARD.getButtons());
            }
        }

        return null;
    }

    /**
     * Определение параметра для вакансии
     * @param parsedCommand
     * @return
     */
    private FilterKey getFilterInfo(ParsedCommand parsedCommand) {
        return FilterKey.valueOf(parsedCommand.getText().trim().split("=")[0].toUpperCase(Locale.ROOT));
    }

    /**
     * Получение пары с ключом и значением фильтра
     * @param parsedCommand
     * @return
     */
    public Pair<String, String> getFilterData(ParsedCommand parsedCommand) {

        if (parsedCommand.getText().length() != 0) {

            String filterKey = parsedCommand.getText().split("=")[0];
            String filterValue = parsedCommand.getText().split("=")[1];

            return new Pair<>(filterKey, filterValue);
        }
        return new Pair<>("", "");
    }

}
