package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.AuthCommand;
import command.ParsedCommand;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.AuthChecker;

/**
 * Класс обработки команды авторизации
 */
public class AuthHandler extends AbstractHandler {
    public AuthHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {
        bot.sendQueue.add(getMessage(chatId,update,bot));
    }

    /**
     * Метод для отправки сообщения с ссылкой на авторизцию
     * @param chatId идентификатор чата телеграмм
     * @param update эвент телеграмм
     * @param bot телеграм бот
     * @return Сообщение для телеграмм бота
     */
    public SendMessage getMessage(String chatId,Update update,HeadHunterBot bot) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(AuthCommand.getAuthMessage());
        // Запускаем поток для получения кода авторизации от сервера
        Thread authChecker = new Thread(new AuthChecker(update,chatId,bot));
        authChecker.start();

        return message;
    }


}
