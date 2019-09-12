package ua.com.makarenko.commands;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import ua.com.makarenko.view.Message;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ExitTest {
    private Message message;
    private Command command;

    @Before
    public void setUp() {
        message = mock(Message.class);
        command = new Exit(message);
    }

    @Test
    public void testExitTrue() {
        boolean exitTrue = command.beginCommand("exit");
        assertTrue(exitTrue);
    }

    @Test
    public void testExitException() {
        try {
            command.executeCommand("exit");
            fail();
        } catch (Exit e) {
        }
        verify(message).write("Goodbye, thank you for using our product");
    }
}