package entity.commands;

import entity.plugs.Plugs;
import entity.user.User;
/**
 * класс команды пользователя
 */
public class UserCommand implements ICommand {
    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();

        info.append("Команда /user выводит информацию о пользователе\n");

        return info.toString();
    }

    /**
     * Выводит информацию о пользователе
     * @param args аргументы из командной строки
     */
    @Override
    public void execute(String[] args) {
        User newUser = Plugs.getUsers().get(2);

        System.out.println(newUser.getInfo());
    }

    @Override
    public String getName() {
        return "/user";
    }
}
