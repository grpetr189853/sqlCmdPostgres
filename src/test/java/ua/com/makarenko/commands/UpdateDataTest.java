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

public class UpdateDataTest {
    private Message message;
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        managerDAO = mock(ManagerDAO.class);
        command = new UpdateData(message, managerDAO);
    }

    @Test
    public void testBeginCommand() {
        boolean beginCommand = command.beginCommand("update|");
        assertTrue(beginCommand);
    }

    @Test
    public void testBeginCommandError() {
        boolean beginCommandError = command.beginCommand("updatte|");
        assertFalse(beginCommandError);
    }

    @Test
    public void testWithoutParameterCommand() {
        boolean withoutParameter = command.beginCommand("update");
        assertFalse(withoutParameter);
    }

    @Test
    public void testExecutionCommandSuccessful() {
        command.executeCommand("update|car|id|1|name|sens");
        String tableName = "car";
        String keyName = "id";
        String keyValue = "1";
        Map<String, Object> columnData = new LinkedHashMap<>();
        columnData.put("name", "sens");

        verify(managerDAO).updateData(tableName, keyName, keyValue, columnData);
        verify(message).write(DescriptionMessage.UPDATE_DATA.getDescription());
    }

    @Test
    public void testExecutionCommandErrorParameters() {
        try {
            command.executeCommand("update|car|");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(
                    DescriptionMessage.WRONG_UPDATE_DATA.getDescription(), "update|car|"), e.getMessage()
            );
        }
    }
}