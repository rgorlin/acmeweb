package commandtests;

import com.acme.commands.BasicServerStatusCmd;
import com.acme.executors.SerialExecutor;
import com.acme.statusmgr.beans.ServerStatus;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

public class BasicServerStatusCmdTest {

    private static final String template = "Server Status requested by %s";

    @Test
    public void getResults() {

        BasicServerStatusCmd cmd = new BasicServerStatusCmd(1, template, "Bob");
        SerialExecutor exc = new SerialExecutor(cmd);
        exc.handleImmediately();
        ServerStatus results = (ServerStatus) cmd.getResult();

        assertEquals(String.format(template, "Bob"), results.getContentHeader());
        assertEquals(1, results.getId());
        assertEquals("Server is up", results.getStatusDesc());

    }
}
