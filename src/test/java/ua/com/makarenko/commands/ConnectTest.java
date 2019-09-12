package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConnectTest {
    private Message message;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        command = new Connect(message);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("connect|");
        assertTrue(beginCommand);
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommand = command.beginCommand("connec|");
        assertFalse(beginCommand);
    }

    @Test
    public void testBeginCommandWithoutParameter() {
        boolean beginCommand = command.beginCommand("connect");
        assertFalse(beginCommand);
    }

    @Test
    public void testExecutionCommandSuccessful() {
        command.executeCommand("connect|tested|postgres|postgres");
        verify(message).write("Database connection 'tested' was successful");
    }

    @Test
    public void testExecutionCommandCountParameter() {
        try {
            command.executeCommand("connect|tested|postgres");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(
                    DescriptionMessage.WRONG_CONNECT.getDescription(), "connect|tested|postgres"), e.getMessage()
            );
        }
    }
}

