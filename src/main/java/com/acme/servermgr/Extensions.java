package com.acme.servermgr;

/**
 * used to check the extensions a server is using.
 * @see IMonitorableServerDecorator
 * in the futre will be outfitted with more than jusat return a string
 */
public class Extensions extends IMonitorableServerDecorator {
    public Extensions(IMonitorableServer IMonitorableServer) {
        super(IMonitorableServer);
    }
    @Override
    public String determineServerStatus(){
        return  super.determineServerStatus() + ", and is " + getExtensionsStatus();
    }
}
