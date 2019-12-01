package statusmgr.decorators;

import com.acme.servermgr.ServerManager;
import com.acme.servermgr.SlowRealMonitor;
import com.acme.statusmgr.beans.ServerStatus;
import org.springframework.beans.factory.annotation.Configurable;

public class BasicServerStatus extends ServerStatus {
    public BasicServerStatus(long id, String header){
        super(id, header);
    }

    @Override
    public String getStatusDesc() {
        return this.serverManager.getCurrentServerStatus();
    }
}
