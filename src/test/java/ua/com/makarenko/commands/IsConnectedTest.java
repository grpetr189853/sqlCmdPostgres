package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IsConnectedTest {
    private Message message;
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        managerDAO = mock(ManagerDAO.class);
        command = new IsConnected(message, managerDAO);
    }

    @Test
    public void beginCommand() {
        command.beginCommand("connect|sqlcmd|postgres|postgres");
        verify(managerDAO).isConnected();
    }

    @Test
    public void executionCommand() {
        command.executeCommand("connect");
        verify(message).write(String.format(
                DescriptionMessage.IS_CONNECTED.getDescription(), "connect"));
    }
}