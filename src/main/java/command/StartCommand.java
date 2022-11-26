package command;


import keyboard.Keyboard;
import keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class StartCommand extends BotCommand {
    public StartCommand() {
        super(CommandInfo.START_DESCRIPTION.getValue(), CommandInfo.START_HELPMESSAGE.getValue());
    }

    /**
     *
     * @return клавиатура для телеграм бота
     */
    public InlineKeyboardMarkup setKeyboard() {
        return KeyboardCreator.getInlineKeyboard(1, Keyboard.START_KEYBOARD.getButtons());
    };

}
