package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;

public class OpenTable implements Command {
    private ManagerDAO managerDAO;

    public OpenTable(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("open|");
    }

    @Override
    public void executeCommand(String command) {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new IllegalArgumentException(
                    String.format(DescriptionMessage.WRONG_OPEN_TABLE.getDescription(), command));
        }

        String tableName = data[1];
        managerDAO.openTable(tableName);
    }
}
