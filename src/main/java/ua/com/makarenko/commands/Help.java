package ua.com.makarenko.commands;

import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class Help implements Command {
    private Message message;
    private String helpFile = "src/main/resources/help.txt";

    public Help(Message message) {
        this.message = message;
    }

    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("help");
    }

    @Override
    public void executeCommand(String command) {
        if (!command.equals("help")) {
            throw new IllegalArgumentException(DescriptionMessage.COMMAND_NOT_EXIST.getDescription());
        }

        try {
            List<String> readHelpFiles = Files.readAllLines(Paths.get(helpFile));
            Iterator<String> iterator = readHelpFiles.iterator();

            while (iterator.hasNext()) {
                message.write(iterator.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(DescriptionMessage.FILE_NOT_FIND.getDescription());
        }
    }
}
