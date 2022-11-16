package entity.commands;

/**
 * Класс комманд телеграм-бота
 **/
public interface ICommand {
    /**
     * Метод для получения в консоли информации о команде
     **/
    String getInfo();

    /**
     * Метод для выполнения команды
     * @param args аргументы из командной строки
     */
    void execute(String[] args);

    /**
     *
     * @return название команды
     */
    String getName();
}
