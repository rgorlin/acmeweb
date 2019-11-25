package com.acme.servermgr;

/**
 * the Status Decorator that makes the Decorator pattern possible, anything that extends this returns server info.
 * @see IMonitorableServer
 * @see Memory, for example
 */
public abstract class IMonitorableServerDecorator implements IMonitorableServer {
    private IMonitorableServer IMonitorableServer;
    public IMonitorableServerDecorator(IMonitorableServer IMonitorableServer){
        this.IMonitorableServer = IMonitorableServer;
    }
    @Override
    public String determineServerStatus(){
        return IMonitorableServer.determineServerStatus();
    }
    public String getMemoryStatus(){
        return "its memory is running low";
    }
    public String getExtensionsStatus(){
        return "using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }
    public String getOperationsStatus(){
        return "is operating normally";
    }



}
