package com.acme.decorators.complex;

import com.acme.statusmgr.beans.ServerStatus;

/**
 * the Basic Server status used with the decorators
 * @see ServerStatus
 * @see  Memory for an example
 */
public class BasicServerStatus extends ServerStatus {
    public BasicServerStatus(long id, String header){
        super(id, header);
    }

    @Override
    public String getStatusDesc() {
        return this.serverManager.getCurrentServerStatus();
    }
}
