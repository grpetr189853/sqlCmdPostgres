package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class DropTableTest {
    private Message message;
    private ManagerDAO databaseManager;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        databaseManager = mock(ManagerDAO.class);
        command = new DropTable(message, databaseManager);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("drop|");
        assertTrue(beginCommand);
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommand = command.beginCommand("droop|");
        assertFalse(beginCommand);
    }

    @Test
    public void testWithoutParameterCommand() {
        boolean beginCommand = command.beginCommand("drop");
        assertFalse(beginCommand);
    }

    @Test
    public void testDropTableSuccessful() {
        when(message.read()).thenReturn("car");
        command.executeCommand("drop|car");
        verify(message).write("You want to delete the table 'car'? Enter the name of the table to confirm");
        verify(databaseManager).dropTable("car");
        verify(message).write("Table 'car' successfully deleted");
    }

    @Test
    public void testDropTableNotSuccessful() {
        when(message.read()).thenReturn("d");
        command.executeCommand("drop|car");
        verify(message).write("You want to delete the table 'car'? Enter the name of the table to confirm");
        verify(message).write("deletion canceled");
    }

    @Test
    public void testWithParameterCommandError() {
        try {
            command.executeCommand("drop|");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(
                    DescriptionMessage.WRONG_DROP_TABLE.getDescription(), "drop|"), e.getMessage()
            );
        }
    }
}