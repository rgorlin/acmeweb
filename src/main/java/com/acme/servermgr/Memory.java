package com.acme.servermgr;

/**
 * used to check the memory of the server
 * @see IMonitorableServerDecorator
 * in the futre will be outfitted with more than jusat return a string
 */
public class Memory extends IMonitorableServerDecorator {
    public Memory(IMonitorableServer IMonitorableServer) {
        super(IMonitorableServer);
    }
    @Override
    public String determineServerStatus(){
        return super.determineServerStatus() + ", and " + getMemoryStatus();

    }

}
