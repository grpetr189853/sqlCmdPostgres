package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

public class ClearTable implements Command {
    private Message message;
    private ManagerDAO managerDAO;

    public ClearTable(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void executeCommand(String command) {
        String data[] = command.split("\\|");
        if (data.length != 2) {
            throw new IllegalArgumentException(
                    String.format(DescriptionMessage.WRONG_CLEAR_TABLE.getDescription(), command));
        }

        String tableName = data[1];
        if (!confirmed(tableName)) {
            return;
        }

        managerDAO.clearTable(tableName);
        message.write(String.format(DescriptionMessage.CLEAR_TABLE.getDescription(), tableName));
    }

    private boolean confirmed(String tableName) {
        message.write(String.format(DescriptionMessage.CONFIRM_CLEAR_TABLE.getDescription(), tableName));
        String verification = message.read();
        if (verification.equals(tableName)) {
            return true;
        }
        message.write(DescriptionMessage.CLEAR_CANCELED.getDescription());
        return false;
    }
}
