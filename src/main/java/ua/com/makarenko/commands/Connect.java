package ua.com.makarenko.commands;

import ua.com.makarenko.model.ConnectDatabase;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

public class Connect implements Command {
    private Message message;

    public Connect(Message message) {
        this.message = message;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void executeCommand(String command) {
        String[] data = command.split("\\|");

        if (data.length != 4) {
            throw new IllegalArgumentException(
                    String.format(DescriptionMessage.WRONG_CONNECT.getDescription(), command));
        }

        String database = data[1];
        String login = data[2];
        String password = data[3];

        ConnectDatabase.setupConnection(database, login, password);
        message.write(String.format(DescriptionMessage.CONNECT_SUCCESSFUL.getDescription(), database));
    }
}
