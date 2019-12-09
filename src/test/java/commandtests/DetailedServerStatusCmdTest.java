package commandtests;

import com.acme.commands.DetailedServerStatusCmd;
import com.acme.executors.SerialExecutor;
import com.acme.statusmgr.beans.ServerStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DetailedServerStatusCmdTest {

    private static final String template = "Server Status requested by %s";
    List<String> details = new ArrayList<>();


    @Test
    public void getResults() {

        details.add("operations");
        details.add("operations");

        DetailedServerStatusCmd cmd = new DetailedServerStatusCmd(1, template, "eden",
                details,"simple");
        SerialExecutor exc = new SerialExecutor(cmd);
        exc.handleImmediately();

        ServerStatus results = (ServerStatus) cmd.getResult();

        assertEquals(null, results.getContentHeader());
        assertEquals( 0,results.getId());
        assertEquals("Server is up, and is operating normally, and is operating normally",
                results.getStatusDesc());

    }
}
