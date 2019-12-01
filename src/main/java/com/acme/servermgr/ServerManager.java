package com.acme.servermgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple methods for use in school project
 */
@Component
public class ServerManager {

    /**
     * Reference to a class that knows how to get details about what we are interested in on a server
     */
    @Autowired
    private  IMonitorableServer monitor;


    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    public String getCurrentServerStatus() {

         String  status = monitor.getCurrentServerStatus();

        return status;
    }

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    public static String getMemoryStatus(){
        return "memory is running low";
    }

    public static String getExtensionsStatus(){
        return "using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }

    public static String getOperationsStatus(){
        return "is operating normally";
    }
}
