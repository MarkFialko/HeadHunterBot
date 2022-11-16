package entity;

import entity.commands.CommandInitializer;
import entity.commands.ICommand;

import java.util.HashMap;
import java.util.Scanner;

/**
 * класс телеграм бота
 */
public class TelegramBot {
    /**
     * отвечает за старт бота
     */
    public static void start() {
        HashMap<String, ICommand> commands = CommandInitializer.getAvailableCommands();

        System.out.println("[LOG] bot started...\n");
        System.out.println("Для того, чтобы узнать, что я делаю, набери команду: /help");

        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine().trim();
                if (commands.containsKey(userInput.split(" ")[0])) {
                    commands.get(userInput.split(" ")[0]).execute(userInput.split(" "));
                } else {
                    System.out.println("[ERR] Такой команды нет, введите: /help\n");
                }
            }
        } catch (Exception e) {
            System.out.println("[ERR] Got exception while waiting user input: " + e.getMessage());
        }
    }
}
