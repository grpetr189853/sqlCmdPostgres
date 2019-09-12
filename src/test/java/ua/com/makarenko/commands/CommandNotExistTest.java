package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandNotExistTest {
    private Message message;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        command = new CommandNotExist(message);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("");
        assertTrue(beginCommand);
    }

    @Test
    public void testExecutionCommand() {
        command.executeCommand("is");
        verify(message).write(String.format(DescriptionMessage.COMMAND_NOT_EXIST.getDescription(), "is"));
    }
}
