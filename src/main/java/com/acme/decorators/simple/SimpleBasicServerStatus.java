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
    public SimpleBasicServerStatus(long id, String header){
        super(id, header);
    }

    @Override
    public String getStatusDesc() {
        return this.serverManager.getCurrentServerStatus();
    }
}
