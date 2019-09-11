package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

public class IsConnected implements Command {
    private Message message;
    private ManagerDAO managerDAO;

    public IsConnected(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return !managerDAO.isConnected();
    }

    @Override
    public void executeCommand(String command) {
        message.write(DescriptionMessage.IS_CONNECTED.getDescription());
    }
}
