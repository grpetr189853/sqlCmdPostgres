package ua.com.makarenko.controller;

import ua.com.makarenko.commands.*;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private List<Command> commands;
    private Message message;

    public Controller(Message message, ManagerDAO managerDAO) {
        this.message = message;
        this.commands = new ArrayList<>(Arrays.asList(
                new Connect(message),
                new CreateTable(message, managerDAO),
                new ListTables(message, managerDAO),
                new DropTable(message, managerDAO),
                new ClearTable(message, managerDAO),
                new OpenTable(managerDAO)
        ));
    }

    public void run() {
        try {
            message.write("Welcome to program 'SQLCMD'");
            message.write("To view commands enter: help");

            while (true) {
                String input = message.read();
                for (Command command : commands) {
                    try {
                        if (command.beginCommand(input)) {
                            command.executeCommand(input);
                            break;
                        }
                    } catch (RuntimeException e) {
                        if (e instanceof ExitException) throw e;
                        printError(e);
                        break;
                    }
                }
                message.write("Enter command (or help):");
            }
        } catch (ExitException e) {}
    }

    private void printError(RuntimeException e) {
        String error = e.getMessage();
        Throwable cause = e.getCause();
        if (cause != null) {
            error += " " + cause.getMessage();
        }
        message.write("Fail! because of: " + error);
    }
}
