package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListTablesTest {
    private Message message;
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        managerDAO = mock(ManagerDAO.class);
        command = new ListTables(message, managerDAO);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("tables");
        assertTrue(beginCommand);
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommandError = command.beginCommand("tabes");
        assertFalse(beginCommandError);
    }

    @Test
    public void testExecutionCommand() {
        command.executeCommand("tables");
        verify(managerDAO).listTables();
    }

    @Test
    public void testExecutionCommandNullTables() {
        command.executeCommand("tables");
        verify(managerDAO).listTables();
        verify(message).write(DescriptionMessage.DATABASE_NOT_TABLES.getDescription());
    }
}