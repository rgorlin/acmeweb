package com.acme.servermgr;

import statusmgr.decorators.Memory;

/**
 * the Status Decorator that makes the Decorator pattern possible, anything that extends this returns server info.
 * @see IMonitorableServer
 * @see Memory , for example
 */
public abstract class IMonitorableServerDecorator implements IMonitorableServer {
    private IMonitorableServer IMonitorableServer;
    public IMonitorableServerDecorator(IMonitorableServer IMonitorableServer){
        this.IMonitorableServer = IMonitorableServer;
    }
    @Override
    public String getCurrentServerStatus(){
        return IMonitorableServer.getCurrentServerStatus();
    }




}
