package com.acme.servermgr;

/**
 * used to check how the server is operating
 * @see IMonitorableServerDecorator
 *  in the futre will be outfitted with more than jusat return a string
 */
public class Operations extends IMonitorableServerDecorator {
    public Operations(IMonitorableServer IMonitorableServer) {
        super(IMonitorableServer);
    }
    @Override
    public String getCurrentServerStatus(){
        return super.getCurrentServerStatus() + ", and " + getOperationsStatus();
    }

}
