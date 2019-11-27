package statusmgr.decorators;

import servermgr.ServerManager;
import statusmgr.beans.ServerStatus;

/**
 * used to check the memory of the server
 * @see ServerStatus
 * in the futre will be outfitted with more than jusat return a string
 */
public class Memory extends ServerStatus {

    private final ServerStatus baseComp;

    public Memory(long id, String header, ServerStatus baseComp){
        super(id, header);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return baseComp.getStatusDesc() + " , and " + ServerManager.getMemory();
    }

}
