package command;

/**
 * Абстрактный класс команд телеграмм бота
 */
public abstract class BotCommand {

    private String description;
    private String messageForHelp;

    public BotCommand(String description,String messageForHelp) {
        this.description = description;
        this.messageForHelp = messageForHelp;
    }


    public String getDescription() {
        return description;
    }

    public String getMessageForHelp() {
        return messageForHelp;
    }
}
