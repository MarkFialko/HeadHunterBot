package command;

import keyboard.Keyboard;
import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * HelpCommand телеграм бота
 */
public class HelpCommand extends BotCommand {
    private final String PREFIX_FOR_COMMAND = "/";

    public HelpCommand() {
        super(CommandInfo.HELP_DESCRIPTION.getValue(), CommandInfo.HELP_HELPMESSAGE.getValue());
    }

    /**
     *
     * @param parsedCommand - спарсенная команда
     * @return сообщение с помощью по команде
     */
    public String getHelp(ParsedCommand parsedCommand) {

        String helpText = PREFIX_FOR_COMMAND + parsedCommand.getText();

        Command commandForHelp = new Parser().getParsedCommand(helpText).command;

        if (commandForHelp == Command.NONE && helpText.length() > 1) return CommandInfo.NONE_COMMAND.getValue();

        return switch (commandForHelp) {
            case START -> new StartCommand().getMessageForHelp();
            case HELP -> new HelpCommand().getMessageForHelp();
            case USER -> new UserCommand().getMessageForHelp();
            case FAVOURITES -> CommandInfo.FAVOURITES_HELPMESSAGE.getValue();
            case VACANCIES -> new VacanciesCommand().getMessageForHelp();
            case AUTH -> CommandInfo.AUTH_HELPMESSAGE.getValue();
            default -> this.getDescription();
        };

    }

    /**
     *
     * @param isValidCommand - флаг, вызвана команда с валидной командой или нет
     * @return клавиатуру для телеграм бота
     */
    public InlineKeyboardMarkup setKeyboard(Boolean isValidCommand) {

        return isValidCommand
                ? KeyboardCreator.getInlineKeyboard(3, Keyboard.HELP_KEYBOARD.getButtons())
                : KeyboardCreator.getInlineKeyboard(1, Keyboard.START_KEYBOARD.getButtons());
    }
}
