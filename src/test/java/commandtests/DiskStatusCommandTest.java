package commandtests;

import com.acme.commands.DiskStatusCmd;
import com.acme.executors.SerialExecutor;
import com.acme.statusmgr.disk.DiskStatus;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class DiskStatusCommandTest {

    private static final String template = "Server Status requested by %s";

    @Test
    public void getResults() {

        DiskStatusCmd cmd = new DiskStatusCmd(1, template, "Ben");
        SerialExecutor executor = new SerialExecutor(cmd);

        executor.handleImmediately();
        DiskStatus results = (DiskStatus) cmd.getResult();

        assertEquals(1, results.getId());
        assertEquals(String.format(template, "Ben"), results.getContentHeader());
        assertTrue(results.getStatusDesc().contains("Total Files Listed:"));

    }
}
