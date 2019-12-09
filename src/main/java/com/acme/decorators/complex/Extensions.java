package com.acme.decorators.complex;


import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * used to check the extensions a server is using.
 * @see ServerStatus
 * in the futre will be outfitted with more than jusat return a string
 */
public class Extensions extends ServerStatus {

    private final ServerStatus base;

    public Extensions(long id, String header, ServerStatus baseComp){
        super(id, header);
        this.base = baseComp;
    }

    @Override
    public String obtainStatusDesc() {
        return base.obtainStatusDesc() + " , and is " + ServerManager.getExtensionsStatus();
    }
}
