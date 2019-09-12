package ua.com.makarenko.commands;

import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

public class Exit extends RuntimeException implements Command {

    private Message message;

    public Exit() {
    }

    public Exit(Message message) {
        this.message = message;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("exit");
    }

    @Override
    public void executeCommand(String command) {
            message.write(DescriptionMessage.EXIT_PROGRAM.getDescription());
            throw new Exit();
    }
}
