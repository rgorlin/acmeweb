package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.beans.factories.SimpleFactory;
import com.acme.statusmgr.beans.factories.StatusFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.acme.servermgr.*;
import com.acme.decorators.complex.BasicServerStatus;

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
    @Autowired
    StatusFactory factory;

    public StatusController(StatusFactory factory) {
        this.factory = factory;
    }


    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ServerStatus showServerStatus(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        System.out.println("*** DEBUG INFO ***" + name);
        return new BasicServerStatus(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/status/detailed", method = RequestMethod.GET)
    public ServerStatus showServerStatusDetails(@RequestParam(value = "details") List<String> details,
                                                @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name,
                                                @RequestParam(value = "levelOfDetail", required = false, defaultValue = "complex") String levelOfDetail) throws BadRequestException {
        if (details == null) {
            throw new BadRequestException("Required List parameter 'details' is not present\",\"path\":\"/server/status/detailed\"");
        }
        long id = counter.incrementAndGet();
        String header = String.format(template, name);
        if (levelOfDetail.equalsIgnoreCase("complex"))
            return factory.getServerStatus(id, header, details);

        else if (levelOfDetail.equalsIgnoreCase("simple")) {
            factory = new SimpleFactory();
            return factory.getServerStatus(0, null, details);
        } else {
            throw new BadRequestException(levelOfDetail + "is not a valid levelOfDetail");
        }
    }
}
