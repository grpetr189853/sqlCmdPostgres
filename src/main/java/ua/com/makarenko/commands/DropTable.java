package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

public class DropTable implements Command {
    private Message message;
    private ManagerDAO managerDAO;

    public DropTable(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("drop|");
    }

    @Override
    public void executeCommand(String command) {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new IllegalArgumentException(
                    String.format(DescriptionMessage.WRONG_DROP_TABLE.getDescription(), command));
        }

        String tableName = data[1];
        if (!confirmed(tableName)) {
            return;
        }

        managerDAO.dropTable(tableName);
        message.write(String.format(DescriptionMessage.DROP_TABLE.getDescription(), tableName));
    }

    private boolean confirmed(String tableName) {
        message.write(String.format(DescriptionMessage.CONFIRM_DROP_TABLE.getDescription(), tableName));
        String verification = message.read();
        if (verification.equals(tableName)) {
            return true;
        }
        message.write(DescriptionMessage.DELETION_CANCELED.getDescription());
        return false;
    }
}
