package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OpenTableTest {
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        managerDAO = mock(ManagerDAO.class);
        command = new OpenTable(managerDAO);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("open|");
        assertTrue(beginCommand);
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommandError = command.beginCommand("openn|");
        assertFalse(beginCommandError);
    }

    @Test
    public void testWithoutParameterCommand() {
        boolean beginCommandWithout = command.beginCommand("open");
        assertFalse(beginCommandWithout);
    }

    @Test
    public void testExecutionCommandSuccessful() {
        command.executeCommand("open|car");
        verify(managerDAO).openTable("car");
    }

    @Test
    public void testWithParameterCommandError() {
        try {
            command.executeCommand("open|");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(
                    DescriptionMessage.WRONG_OPEN_TABLE.getDescription(), "open|"), e.getMessage()
            );
        }
    }
}