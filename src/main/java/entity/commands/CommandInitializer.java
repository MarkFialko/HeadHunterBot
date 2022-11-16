package entity.commands;

import java.util.HashMap;

/**
 * класс для инициализации команд бота
 */
public class CommandInitializer {
    /**
     * @return возварщает хэшмап, ключ - название командыб значение - сама команда
     */
    public static HashMap<String, ICommand> getAvailableCommands() {
        HashMap<String, ICommand> commands = new HashMap<>();

        commands.put("/help", new HelpCommand());
        commands.put("/user", new UserCommand());
        commands.put("/vacancies", new VacanciesCommand());
        commands.put("/filter", new FilterCommand());

        return commands;
    }

}
