package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import java.util.LinkedHashMap;
import java.util.Map;

public class InsertData implements Command {
    private Message message;
    private ManagerDAO managerDAO;

    public InsertData(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("insert|");
    }

    @Override
    public void executeCommand(String command) {
        String[] data = command.split("\\|");
        if (data.length < 3 || data.length % 2 == 1) {
            throw new IllegalArgumentException(String.format(
                    DescriptionMessage.WRONG_INSERT_DATA.getDescription(), command));
        }

        String tableName = data[1];
        Map<String, Object> row = new LinkedHashMap<>();
        for (int i = 2; i < data.length; i += 2) {
            row.put(data[i], data[i + 1]);
        }

        managerDAO.insertData(tableName, row);
        message.write(String.format(DescriptionMessage.INSERT_DATA.getDescription(), tableName));
    }
}
