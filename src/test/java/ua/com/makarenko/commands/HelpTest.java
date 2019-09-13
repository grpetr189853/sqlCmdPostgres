package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.view.Message;

import java.io.File;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class HelpTest {
    private Message message;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        command = new Help(message);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("help");
        assertTrue(beginCommand);
    }

    @Test
    public void readFileIsExists() {
        File file = new File("src/main/resources/help.txt");
        assertTrue(file.exists());
    }
}