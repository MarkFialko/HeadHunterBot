package command;

import filter.FilterData;
import filter.FilterKey;
import javafx.util.Pair;
import keyboard.Keyboard;
import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Класс с описанием команды Вакансии
 */
public class VacanciesCommand extends BotCommand {
    public VacanciesCommand() {
        super("", CommandInfo.VACANCIES_HELPMESSAGE.getValue());
    }

    /**
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
            case EXPERIENCE -> {
                return FilterData.FILTER_EXPERIENCE.getValue();
            }
            case SALARY, SALARY_FROM -> {
                return FilterData.FILTER_SALARY.getValue();
            }
            case GET -> {
                return FilterData.FILTER_GET.getValue();
            }
        }
        return FilterData.FILTER_ERROR.getValue();
    }

    /**
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
                return KeyboardCreator.getInlineKeyboard(1, Keyboard.VACANCIES_EXPERIENCE_KEYBOARD.getButtons());
            }
            case EXPERIENCE -> {
                return KeyboardCreator.getInlineKeyboard(2, Keyboard.VACANCIES_SALARY_KEYBOARD.getButtons());
            }
            case SALARY -> {
                return KeyboardCreator.getInlineKeyboard(2, Keyboard.VACANCIES_GET_KEYBOARD.getButtons());
            }
        }
        return null;
    }

    /**
     * Определение параметра для вакансии
     *
     * @param parsedCommand
     * @return
     */
    private FilterKey getFilterInfo(ParsedCommand parsedCommand) {
        try {
            FilterKey.valueOf(parsedCommand.getText().trim().split("=")[0].toUpperCase(Locale.ROOT));
            return FilterKey.valueOf(parsedCommand.getText().trim().split("=")[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return FilterKey.NONE;
        }

    }

    /**
     * Получение пары с ключом и значением фильтра
     *
     * @param parsedCommand
     * @return
     */
    public List<Pair<String, String>> getFilterData(ParsedCommand parsedCommand) {

        List<Pair<String, String>> listOfFilters = new ArrayList<>();

        if (parsedCommand.getText().length() != 0) {

            List<String> textList = List.of(parsedCommand.getText().split("&"));

            for (String filter : textList) {
                String filterKey = filter.split("=")[0];
                String filterValue = filter.split("=")[1];
                listOfFilters.add(new Pair<>(filterKey, filterValue));
            }


            return listOfFilters;
        }
        return listOfFilters;
    }

}
