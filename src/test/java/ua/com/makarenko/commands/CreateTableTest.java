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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateTableTest {
    private Message message;
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        managerDAO = mock(ManagerDAO.class);
        command = new CreateTable(message, managerDAO);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("create|");
        assertTrue(beginCommand);
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommand = command.beginCommand("creat|");
        assertFalse(beginCommand);
    }

    @Test
    public void testWithoutParameterCommand() {
        boolean beginCommand = command.beginCommand("create");
        assertFalse(beginCommand);
    }

    @Test
    public void testExecutionCommandSuccessful() {
        String tableName = "people";
        String keyName = "id";
        Map<String, String> columns = new LinkedHashMap<>();
        columns.put("name", "text");

        command.executeCommand("create|people|id|name|text");
        verify(managerDAO).createTable(tableName, keyName, columns);
        verify(message).write("Table 'people' created successfully");
    }

    @Test
    public void testExecutionCommandErrorParameters() {
        try {
            command.executeCommand("create|people");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(
                    DescriptionMessage.WRONG_CREATE_TABLE.getDescription(), "create|people"), e.getMessage()
            );
        }
    }
}
