package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import command.ParsedCommand;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import auth.Auth;
import service.AuthChecker;

public class AuthHandler extends AbstractHandler {
    public AuthHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) throws JsonProcessingException {
        bot.sendQueue.add(getMessage(chatId,update,bot));
    }

    public SendMessage getMessage(String chatId,Update update,HeadHunterBot bot) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(Auth.getAuthorizationUrl());

        Thread authChecker = new Thread(new AuthChecker(update,chatId,bot));
        authChecker.start();

        return message;
    }


}
