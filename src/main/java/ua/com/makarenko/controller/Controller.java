package ua.com.makarenko.controller;

import ua.com.makarenko.commands.*;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    private List<Command> commands;
    private Message message;

    public Controller(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.commands = new LinkedList<>(Arrays.asList(
                new Connect(message),
                new Help(message),
                new Exit(message),
                new IsConnected(message, managerDAO),
                new CreateTable(message, managerDAO),
                new ListTables(message, managerDAO),
                new DropTable(message, managerDAO),
                new ClearTable(message, managerDAO),
                new OpenTable(managerDAO),
                new InsertData(message, managerDAO),
                new UpdateData(message, managerDAO),
                new DeleteData(message, managerDAO),
                new CommandNotExist(message)
        ));
    }

    public void run() {
        try {
            message.write(DescriptionMessage.WELCOME_MESSAGE.getDescription());

            boolean contains = commands.contains(new Exit(message));
            while (!contains) {
                String input = message.read();
                for (Command command : commands) {
                    try {
                        if (command.beginCommand(input)) {
                            command.executeCommand(input);
                            break;
                        }
                    } catch (RuntimeException e) {
                        if(e instanceof Exit) throw e;
                        message.write(DescriptionMessage.FAIL.getDescription() + e.getMessage());
                        break;
                    }
                }
                message.write(DescriptionMessage.ENTER_COMMAND.getDescription());
            }
        } catch (Exit e) {}
    }
}
