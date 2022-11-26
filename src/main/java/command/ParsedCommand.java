package command;


/**
 * Класс спарсенной команды
 */
public class ParsedCommand {
    Command command = Command.NONE;
    String commandText = "";

    public ParsedCommand(Command command, String commandText) {
        this.command = command;
        this.commandText = commandText;
    }

    public void setText(String commandText) {
        this.commandText = commandText;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getText() {
        return commandText;
    }

    public Command getCommand() {
        return command;
    }

}
