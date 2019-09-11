package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

public class DeleteData implements Command {
    private Message message;
    private ManagerDAO managerDAO;

    public DeleteData(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("delete|");
    }

    @Override
    public void executeCommand(String command) {
        String[] data = command.split("\\|");
        if (data.length != 4) {
            throw new IllegalArgumentException(String.format(
                    DescriptionMessage.WRONG_DELETE_DATA.getDescription(), command));
        }

        String tableName = data[1];
        String columnName = data[2];
        String columnValue = data[3];

        managerDAO.deleteData(tableName, columnName, columnValue);
        message.write(DescriptionMessage.DELETE_DATA.getDescription());
    }
}
