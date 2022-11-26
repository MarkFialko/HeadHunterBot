package handler;

import command.ParsedCommand;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.Notify;

/**
 * Класс для обработки команды уведомлений
 */
public class NotifyHandler extends AbstractHandler {

    public NotifyHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) {
        Thread thread = new Thread(new Notify(bot, chatId));
        thread.start();
    }
}