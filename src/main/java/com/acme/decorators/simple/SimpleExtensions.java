package com.acme.decorators.simple;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;
/**
 * used to check the extensions a server is using.
 * @see ServerStatus
 * to be called when a simple level of detial is requsted, will have its id set to zero and header to null,
 * and therefor not shown
 */
public class SimpleExtensions extends ServerStatus {

    private ServerStatus baseComp;

    public SimpleExtensions(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String obtainStatusDesc() {
        return (baseComp.obtainStatusDesc() + ", and is using these extensions" + this.serverManager.getExtensionsStatus());
    }


    @Override
    public String getContentHeader(){
        return null;
    }

    @Override
    public long getId(){
        return 0;
    }
}
