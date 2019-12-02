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
    private ServerStatus base;
    public SimpleExtensions(long id, String header,ServerStatus base) {
        super(id, header);
        this.base=base;
    }

    @Override
    public String getStatusDesc() {
        return base.getStatusDesc() + " , and is " + ServerManager.getExtensionsStatus();
    }
}
