package com.acme.statusmgr;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.commands.BasicServerStatusCmd;
import com.acme.commands.DetailedServerStatusCmd;
import com.acme.commands.DiskStatusCmd;
import com.acme.executors.SerialExecutor;
import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.beans.StatusResponce;
import com.acme.statusmgr.beans.factories.SimpleFactory;
import com.acme.statusmgr.beans.factories.StatusFactory;
import com.acme.statusmgr.disk.InvalidNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.acme.servermgr.*;
import com.acme.decorators.complex.BasicServerStatus;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Controller for all web/REST requests about the status of servers
 * <p>
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 * All start with /server
 * /status  will give back status of server
 * a param of 'name' specifies a requestor name to appear in response
 * <p>
 * Examples:
 * http://localhost:8080/server/status
 * <p>
 * http://localhost:8080/server/status?name=Noach,Reuven
 * <p>
 * http://localhost:8080/server/status/detailed?details=memory
 * <p>
 * http://localhost:8080/server/status/detailed?details=extensions&levelOfDetail=simple
 * <p>
 * For use with the DiskCommand
 * http://localhost:8080/server/disk/status?name=Billy
 * <p>
 */

@RestController
@RequestMapping("/server")
public class StatusController {

    static {
        // For debug/demo purposes only, dump out class path to stdout to show where resources will come from
        System.out.println("*** JAVA CLASS PATH***\n" +
                System.getProperty("java.class.path").replace(":", "      :      ") + "***********\n");
    }

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();


    @RequestMapping(value = "/status", method = GET)
    public StatusResponce showServerStatus(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        System.out.println("*** DEBUG INFO ***" + name);
        BasicServerStatusCmd cmd = new BasicServerStatusCmd(counter.incrementAndGet(), template, name);
        SerialExecutor exc = new SerialExecutor(cmd);
        exc.handleImmediately();
        return cmd.getResult();

    }

    @RequestMapping(value = "/status/detailed", method = GET)
    public StatusResponce showServerStatusDetails(@RequestParam(value = "details") List<String> details,
                                                @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name,
                                                @RequestParam(value = "levelOfDetail", required = false, defaultValue = "complex") String levelOfDetail) throws BadRequestException {
        if (details == null) {
            throw new BadRequestException("Required List parameter 'details' is not present\",\"path\":\"/server/status/detailed\"");
        }
        long id = counter.incrementAndGet();
        DetailedServerStatusCmd cmd = new DetailedServerStatusCmd(id,template,name,details,levelOfDetail);
        SerialExecutor exc = new SerialExecutor(cmd);
        exc.handleImmediately();
        return cmd.getResult();
    }
    @RequestMapping(value = "/disk/status", method = GET)
    public StatusResponce showDiskStatus(
            @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name )
    {
            DiskStatusCmd cmd = new DiskStatusCmd(counter.incrementAndGet(), template, name);
            SerialExecutor executor = new SerialExecutor(cmd);
            executor.handleImmediately();
            return cmd.getResult();

    }
}
