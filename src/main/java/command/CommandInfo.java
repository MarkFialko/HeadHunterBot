package command;

/**
 * Enum с описаниями команд помощи и их запуска
 */
public enum CommandInfo {
    HELP_DESCRIPTION("Для получения справки по конкретной команде,нажмите на кнопку с командой, либо введите " +
            "/help [COMMAND], где COMMAND - интересующая Вас команда."),

    HELP_HELPMESSAGE("Команда /help выводит справку по выбранной команде."),
    USER_HELPMESSAGE("Команда /user выводит информацию о пользователе."),
    VACANCIES_HELPMESSAGE("Команда /vacancies выводит информацию о новых вакансиях."),

    START_DESCRIPTION("Привет! Этот бот создан для удобного отслеживания вакансий с сайта hh.ru.\n" +
            "Бот позволяет добавлять вакансии в избранное, получать уведомления и отслеживать свой профиль.\n" +
            "Нажмите кнопку \"Помощь\" или введите /help для получения справки."),

    START_HELPMESSAGE("Команда /start запускает бота."),

    NOTIFY_HELPMESSAGE("Команда /notify отправляет вам уведомление с новыми вакансиями спустя 5 секунд."),

    NONE_COMMAND("Неверная команда, нажмите кнопку \"Помощь\" или введите /help для получения справки.");

    private String value;

    CommandInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
