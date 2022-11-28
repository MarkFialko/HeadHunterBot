package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import entity.HeadHunterBot;
import entity.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки команды пользователя
 */
public class UserHandler extends AbstractHandler {
    public UserHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {
        bot.sendQueue.add(getUserInfo(chatId, update));

    }

    @Override
    public AbstractHandler setBot(HeadHunterBot bot) {
        return this;
    }

    /**
     * Получить информацию о пользователе
     * @param chatId чат телеграм
     * @param update эвент телеграм
     * @return сообщение для телеграм бота
     */
    private SendMessage getUserInfo(String chatId, Update update) {
        SendMessage message = new SendMessage();
        Chat chatData = update.getMessage().getChat();
        User user = new User(chatData.getFirstName(), chatData.getLastName());

        message.setChatId(chatId);
        message.setText(user.toString());

        return message;
    }
}
