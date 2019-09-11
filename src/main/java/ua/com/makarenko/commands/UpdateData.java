package ua.com.makarenko.commands;

import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateData implements Command {
    private Message message;
    private ManagerDAO managerDAO;

    public UpdateData(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.managerDAO = managerDAO;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("update|");
    }

    @Override
    public void executeCommand(String command) {
        String[] data = command.split("\\|");
        if (data.length < 6 || data.length % 2 == 1) {
            throw new IllegalArgumentException(String.format(
                    DescriptionMessage.WRONG_UPDATE_DATA.getDescription(), command));
        }

        String tableName = data[1];
        String keyName = data[2];
        String keyValue = data[3];
        Map<String, Object> columnData = new LinkedHashMap<>();
        for (int index = 4; index < data.length; index += 2) {
            columnData.put(data[index], data[index + 1]);
        }
        managerDAO.updateData(tableName, keyName, keyValue, columnData);
        message.write(DescriptionMessage.UPDATE_DATA.getDescription());
    }
}
