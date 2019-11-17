package statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMethod;
import servermgr.BadRequestException;
import statusmgr.beans.ServerStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach,Reuven
 *
 *  http://localhost:8080/server/status/detailed?details=memory
 *
 */

@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();

    @RequestMapping("/status" )
    public ServerStatus showServerStatus (@RequestParam(value="details",defaultValue = "NONE", required=false) List<String> details,@RequestParam(value="name", defaultValue="Anonymous") String name) {
        System.out.println("*** DEBUG INFO ***" + name +"  details=  "+ details  );
        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name,details));
    }
    @RequestMapping(value = "/status/detailed" , method = RequestMethod.GET)
    public ServerStatus showServerStatusDetails (@RequestParam(value="details") List<String> details,
                                                 @RequestParam(value="name",required = false, defaultValue="Anonymous") String name) throws BadRequestException {
        System.out.println("*** DEBUG INFO ***" + name +"  details=  "+ details  );
        if (details== null){
            throw new BadRequestException("Required List parameter 'details' is not present\",\"path\":\"/server/status/detailed\"");
        }
        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name), details);
    }
}
