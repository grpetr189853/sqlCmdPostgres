package ua.com.makarenko.commands;

public interface Command {

    boolean beginCommand(String command);

    void executeCommand(String command);
}
