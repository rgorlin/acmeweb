package statusmgr.decorators;

import servermgr.ServerManager;
import statusmgr.beans.ServerStatus;

/**
 * used to check the extensions a server is using.
 * @see ServerStatus
 * in the futre will be outfitted with more than jusat return a string
 */
public class Extensions extends ServerStatus {

    private final ServerStatus baseComp;

    public Extensions(long id, String header, ServerStatus baseComp){
        super(id, header);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return baseComp.getStatusDesc() + " , and is using these extensions - " + ServerManager.getExtensions();
    }
}
