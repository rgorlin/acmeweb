package com.acme.decorators.simple;

import com.acme.decorators.complex.Memory;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * the Basic Server status used with the decorators for the simplified version of Server Status
 * used when a user wants to see just the StatusDesc and nothing else.
 * @see ServerStatus
 * @see  SimpleMemory for an example
 */
public class SimpleBasicServerStatus extends ServerStatus {

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     *
     * @param id            a numeric identifier/counter of which request this
     * @param contentHeader info about the request
     */
    public SimpleBasicServerStatus(long id, String contentHeader) {
        super(id, contentHeader);
    }

    @Override
    public String obtainStatusDesc() {
        return this.serverManager.getCurrentServerStatus();
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
