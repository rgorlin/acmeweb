package statusmgr.decorators;

import servermgr.ServerManager;
import statusmgr.beans.ServerStatus;

/**
 * used to check how the server is operating
 * @see ServerStatus
 *  in the futre will be outfitted with more than jusat return a string
 */
public class Operations extends ServerStatus {

    private final ServerStatus baseComp;

    public Operations(long id, String header, ServerStatus baseComp){
        super(id, header);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
         return baseComp.getStatusDesc() + " , and " + ServerManager.getOperations();
    }
}
