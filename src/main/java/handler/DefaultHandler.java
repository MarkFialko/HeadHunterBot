package handler;

import command.Command;
import command.CommandInfo;
import command.ParsedCommand;
import command.StartCommand;
import entity.HeadHunterBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки невалидных комманд
 */
public class DefaultHandler extends AbstractHandler {

    public DefaultHandler(HeadHunterBot bot) {
        super(bot);
    }

    @Override
    public void operate(String chatId, ParsedCommand parsedCommand, Update update) {
        bot.sendQueue.add(getMessageHelp(chatId,parsedCommand));

    }

    /**
     * Получить справку
     * @param chatID
     * @param parsedCommand
     * @return
     */
    private SendMessage getMessageHelp(String chatID, ParsedCommand parsedCommand) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);
        String text = CommandInfo.NONE_COMMAND.getValue();

        sendMessage.setText(text);
        if (parsedCommand.getText().length() == 0 || parsedCommand.getCommand() == Command.NONE) {
            sendMessage.setReplyMarkup(new StartCommand().setKeyboard());
        }

        return sendMessage;
    }
}
