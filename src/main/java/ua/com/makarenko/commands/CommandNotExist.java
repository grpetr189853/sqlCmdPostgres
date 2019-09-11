package ua.com.makarenko.commands;

import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

public class CommandNotExist implements Command {
    private Message message;

    public CommandNotExist(Message message) {
        this.message = message;
    }

    @Override
    public boolean beginCommand(String command) {
        return true;
    }

    @Override
    public void executeCommand(String command) {
        message.write(String.format(DescriptionMessage.COMMAND_NOT_EXIST.getDescription(),command));
    }
}
