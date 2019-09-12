package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Test;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.DescriptionMessage;
import ua.com.makarenko.view.Message;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteDataTest {
    private Message message;
    private ManagerDAO managerDAO;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        managerDAO = mock(ManagerDAO.class);
        command = new DeleteData(message, managerDAO);
    }

    @Test
    public void testBeginCommand() {
        boolean delete = command.beginCommand("delete|");
        assertTrue(delete);
    }

    @Test
    public void testBeginCommandError() {
        boolean delete = command.beginCommand("delette|");
        assertFalse(delete);
    }

    @Test
    public void testWithoutParameterCommand() {
        boolean delete = command.beginCommand("delete");
        assertFalse(delete);
    }

    @Test
    public void testExecutionCommandSuccessful() {
        String tableName = "car";
        String columnName = "id";
        String columnValue = "1";

        command.executeCommand("delete|car|id|1");
        verify(managerDAO).deleteData(tableName, columnName, columnValue);
        verify(message).write("Record successfully deleted");
    }

    @Test
    public void testWithParameterCommandError() {
        try {
            command.executeCommand("delete|");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(String.format(
                    DescriptionMessage.WRONG_DELETE_DATA.getDescription(), "delete|"), e.getMessage()
            );
        }
    }
}
