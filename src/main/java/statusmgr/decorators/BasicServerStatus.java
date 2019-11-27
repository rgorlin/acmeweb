package statusmgr.decorators;

import servermgr.ServerManager;
import statusmgr.beans.ServerStatus;

public class BasicServerStatus extends ServerStatus {

    public BasicServerStatus(long id, String header){
        super(id, header);
    }

    @Override
    public String getStatusDesc() {
        return ServerManager.getCurrentServerStatus();
    }
}
