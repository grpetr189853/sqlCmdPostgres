package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import java.util.Set;

public class ListTables implements Command {
    Message message;
    ManagerDAO managerDAO;

    public ListTables(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("tables");
    }

    @Override
    public void executeCommand(String command) {
        Set<String> tables = managerDAO.listTables();
        if (tables.size() != 0) {
            message.write(tables.toString());
        } else {
            message.write(DescriptionMessage.DATABASE_NOT_TABLES.getDescription());
        }
    }
}
