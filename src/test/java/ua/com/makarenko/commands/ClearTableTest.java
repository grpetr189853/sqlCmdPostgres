package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ClearTableTest {
    private Message message;
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        managerDAO = mock(ManagerDAO.class);
        command = new ClearTable(message, managerDAO);
    }

    @Test
    public void testClearTableSuccessful() {
        when(message.read()).thenReturn("car");
        command.executeCommand("clear|car");
        verify(message).write("You want to clear the table 'car'? Enter the name of the table to confirm");
        verify(managerDAO).clearTable("car");
        verify(message).write("Table 'car' successfully cleared");
    }

    @Test
    public void testClearTableNotSuccessful() {
        when(message.read()).thenReturn("d");
        command.executeCommand("clear|car");
        verify(message).write("You want to clear the table 'car'? Enter the name of the table to confirm");
        verify(message).write("cleaning canceled");
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommandError = command.beginCommand("clqear|car");
        assertFalse(beginCommandError);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("clear|");
        assertTrue(beginCommand);
    }

    @Test
    public void testWithoutParameterCommand() {
        boolean commandWithout = command.beginCommand("clear");
        assertFalse(commandWithout);
    }

    @Test
    public void testWithParameterCommandError() {
        try {
            command.executeCommand("clear|");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(DescriptionMessage.WRONG_CLEAR_TABLE.getDescription(), "clear|"), e.getMessage());
        }
    }
}
