package entity.commands;

import java.util.HashMap;
/**
 * класс команды для помощи
 */
public class HelpCommand implements ICommand {
    @Override
    public String getInfo() {
        HashMap<String, ICommand> commands = CommandInitializer.getAvailableCommands();

        StringBuilder info = new StringBuilder();

        info.append("Команда /help выводит информацию обо всех доступных командах.\n")
                .append("   Чтобы получить информацию о конкретной команде,\n")
                .append("   введите цепочку: /help [command].\n")
                .append("\nДоступные команды:\n");

        for (ICommand command : commands.values()) {
            info.append("· " + command.getName() + "\n");
        }


        return info.toString();
    }

    /**
     * выводит сообщение-подсказку о команде из аргументов
     * @param args аргументы из командной строки
     */
    @Override
    public void execute(String[] args) {
        HashMap<String, ICommand> commands = CommandInitializer.getAvailableCommands();
        if (args.length != 2) {
            ICommand selectedCommand = commands.get(args[0]);
            System.out.println("\n-> " + selectedCommand.getInfo());
            return;
        }

        String inputCommand = new StringBuilder("/").append(args[1]).toString();

        if (commands.containsKey(inputCommand)) {
            ICommand selectedCommand = commands.get(inputCommand);
            System.out.println("\n-> " + selectedCommand.getInfo());
            return;
        }
        System.out.println("[ERR] Такой команды нет, введите: /help\n");
    }

    @Override
    public String getName() {
        return "/help";
    }
}