package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateTable implements Command {
    private Message message;
    private ManagerDAO managerDAO;

    public CreateTable(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void executeCommand(String command) {
        String[] data = command.split("\\|");
        if (data.length < 3 || data.length % 2 != 1) {
            throw new IllegalArgumentException(
                    String.format(DescriptionMessage.WRONG_CREATE_TABLE.getDescription(), command));
        }

        String tableName = data[1];
        String keyName = data[2];
        Map<String, Object> columns = new LinkedHashMap<>();
        for (int i = 3; i < data.length; i += 2) {
            columns.put(data[i], data[i + 1]);
        }

        managerDAO.createTable(tableName, keyName, columns);
        message.write(String.format(DescriptionMessage.TABLE_CREATED.getDescription(), tableName));
    }
}
