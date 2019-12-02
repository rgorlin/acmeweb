package com.acme.decorators.complex;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;


/**
 * used to check how the server is operating
 * @see ServerStatus
 *  in the futre will be outfitted with more than jusat return a string
 */
public class Operations extends ServerStatus {

    private final ServerStatus base;

    public Operations(long id, String header, ServerStatus base){
        super(id, header);
        this.base = base;
    }

    @Override
    public String getStatusDesc() {
         return base.getStatusDesc() + " , and " + ServerManager.getOperationsStatus();
    }
}
