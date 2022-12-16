package command;

import auth.Auth;

/**
 * Класс телеграм команды авторизации
 */
public class AuthCommand extends BotCommand {

    public AuthCommand(String description, String messageForHelp) {
        super("", "Команда позволяет авторизовать пользователя.");
    }

    /**
     *
     * @return строку для телеграм бота с ссылкой для авторизации
     */
    public static String  getAuthMessage() {
        return "Перейдите по ссылке, чтобы пройти аутентификацию:\n" + Auth.getAuthorizationUrl();
    }
}
