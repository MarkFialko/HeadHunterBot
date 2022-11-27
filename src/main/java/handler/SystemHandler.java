package handler;

import command.*;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки системных комманд (Не выводят ничего, кроме текста и клавиатуры)
 */
public class SystemHandler extends AbstractHandler {
    private final HelpCommand helpCommand = new HelpCommand();
    private final StartCommand startCommand = new StartCommand();

    private final Boolean NOT_VALID_COMMAND = false;
    private final Boolean VALID_COMMAND = true;

    public SystemHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) {
        Command command = parsedCommand.getCommand();

        switch (command) {
            case START -> bot.sendQueue.add(getMessageStart(chatId, parsedCommand));
            case HELP -> bot.sendQueue.add(getMessageHelp(chatId, parsedCommand));
        }
    }

    @Override
    public AbstractHandler setBot(HeadHunterBot bot) {
        return this;
    }

    /**
     * Получить помощь по команде
     *
     * @param chatID        чат телегам
     * @param parsedCommand спарсенная команда
     * @return сообщение для телеграм бота
     */
    private SendMessage getMessageHelp(String chatID, ParsedCommand parsedCommand) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        String text = helpCommand.getHelp(parsedCommand);

        sendMessage.setText(text);

        if (parsedCommand.getText().length() == 0) {
            sendMessage.setReplyMarkup(helpCommand.setKeyboard(VALID_COMMAND));
        }

        if (text == CommandInfo.NONE_COMMAND.getValue()) {
            sendMessage.setReplyMarkup(helpCommand.setKeyboard(NOT_VALID_COMMAND));
        }

        return sendMessage;
    }

    /**
     * Получить информацию о боте
     *
     * @param chatID        чат телегам
     * @param parsedCommand спарсенная команда
     * @return сообщение для телеграм бота
     */
    private SendMessage getMessageStart(String chatID, ParsedCommand parsedCommand) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);
        String text = startCommand.getDescription();

        sendMessage.setText(text);

        if (parsedCommand.getText().length() == 0) {
            sendMessage.setReplyMarkup(startCommand.setKeyboard());
        }

        return sendMessage;
    }

}
