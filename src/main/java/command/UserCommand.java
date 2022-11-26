package command;

/**
 * Класс с описанием команды Пользователя
 */
public class UserCommand extends BotCommand{
    public UserCommand() {
        super("",CommandInfo.USER_HELPMESSAGE.getValue());
    }
}
