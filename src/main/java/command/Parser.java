package command;

import javafx.util.Pair;


/**
 * Класс парсера комманд
 */
public class Parser {
    private final String PREFIX_FOR_COMMAND = "/";

    /**
     * Создает экземпляр ParsedCommand
     * @param text
     * @return экземпляр команды
     */
    public ParsedCommand getParsedCommand(String text) {
        String trimText = "";
        if (text != null) trimText = text.trim();
        ParsedCommand result = new ParsedCommand(Command.NONE, trimText);

        if ("".equals(trimText)) return result;
        Pair<String, String> commandAndText = getDelimitedCommandFromText(trimText);
        if (isCommand(commandAndText.getKey())) {
            String commandForParse = cutCommandFromFullText(commandAndText.getKey());
            Command commandFromText = getCommandFromText(commandForParse);
            result.setText(commandAndText.getValue());
            result.setCommand(commandFromText);
        }
        return result;
    }

    /**
     *
     * @param text
     * @return возвращает команду без слеша
     */
    private String cutCommandFromFullText(String text) {
        return text.substring(1);
    }

    /**
     * Преобразует в Command
     * @param text
     * @return  команда телеграм бота
     */
    private Command getCommandFromText(String text) {
        String upperCaseText = text.toUpperCase().trim();
        Command command = Command.NONE;
        try {
            command = Command.valueOf(upperCaseText);
        } catch (IllegalArgumentException ignored) {

        }
        return command;
    }

    /**
     *
     * @param trimText
     * @return Ключ - команда, значение - текст команды
     */
    private Pair<String, String> getDelimitedCommandFromText(String trimText) {
        Pair<String, String> commandText;

        if (trimText.contains(" ")) {
            int indexOfSpace = trimText.indexOf(" ");
            commandText = new Pair<>(trimText.substring(0, indexOfSpace), trimText.substring(indexOfSpace + 1));
        } else commandText = new Pair<>(trimText, "");
        return commandText;
    }

    private boolean isCommand(String text) {
        return text.startsWith(PREFIX_FOR_COMMAND);
    }

}
