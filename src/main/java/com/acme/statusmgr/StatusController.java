package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.ServerStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import com.acme.servermgr.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import statusmgr.decorators.BasicServerStatus;
import statusmgr.decorators.Extensions;
import statusmgr.decorators.Memory;
import statusmgr.decorators.Operations;

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

    @RequestMapping("/status")
    public ServerStatus showServerStatus(@RequestParam(value = "details", defaultValue = "NONE", required = false) List<String> details, @RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        System.out.println("*** DEBUG INFO ***" + name + "  details=  " + details);
        return new BasicServerStatus(counter.incrementAndGet(),
                String.format(template, name, details));
    }

    @RequestMapping(value = "/status/detailed", method = RequestMethod.GET)
    public ServerStatus showServerStatusDetails(@RequestParam(value = "details") List<String> details,
                                                @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name) throws BadRequestException {
        System.out.println("*** DEBUG INFO ***" + name + "  details=  " + details);
        if (details == null) {
            throw new BadRequestException("Required List parameter 'details' is not present\",\"path\":\"/server/status/detailed\"");
        }
        long id=counter.incrementAndGet();
        String header= String.format(template,name);

        ServerStatus base = new BasicServerStatus(id,header);

        ServerStatus decoratedBase = decorate(id,header,details,base);

        return new ServerStatus(id,header) {
            @Override
            public String getStatusDesc() {
                return decoratedBase.getStatusDesc();
            }
        };
    }

    private ServerStatus decorate(long id, String header, List<String> details, ServerStatus base) {
        for (String s : details) {
            if (s.equalsIgnoreCase("operations")) {
                base = new Operations(id,header,base);
            } else if (s.equalsIgnoreCase("memory")) {
                base = new Memory(id,header,base);
            } else if (s.equalsIgnoreCase("extensions")) {
                base = new Extensions(id,header,base);
            } else {
                throw new BadRequestException(s + " is not a valid details option");
            }
        }
        return base;
    }
}
