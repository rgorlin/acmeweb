package com.acme.decorators.complex;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * used to check the memory of the server
 * @see ServerStatus
 * in the futre will be outfitted with more than jusat return a string
 */
public class Memory extends ServerStatus {

    private final ServerStatus base;

    public Memory(long id, String header, ServerStatus base){
        super(id, header);
        this.base = base;
    }

    @Override
    public String obtainStatusDesc() {
        return base.obtainStatusDesc() + " , and " + ServerManager.getMemoryStatus();
    }

}
