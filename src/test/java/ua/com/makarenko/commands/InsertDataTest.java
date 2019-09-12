package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InsertDataTest {
    private Message message;
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        managerDAO = mock(ManagerDAO.class);
        command = new InsertData(message, managerDAO);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("insert|");
        assertTrue(beginCommand);
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommandError = command.beginCommand("insssert|");
        assertFalse(beginCommandError);
    }

    @Test
    public void testWithoutParameterCommand() {
        boolean beginCommandError = command.beginCommand("insert");
        assertFalse(beginCommandError);
    }

    @Test
    public void testExecutionCommandSuccessful() {
        String tableName = "car";
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("id", "3");
        row.put("name", "lada");

        command.executeCommand("insert|car|id|3|name|lada");
        verify(managerDAO).insertData(tableName, row);
        verify(message).write(String.format("Records successfully added to the 'car'", tableName));
    }

    @Test
    public void testExecutionCommandErrorParameters() {
        try {
            command.executeCommand("insert|car|");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(
                    DescriptionMessage.WRONG_INSERT_DATA.getDescription(), "insert|car|"), e.getMessage()
            );
        }
    }
}