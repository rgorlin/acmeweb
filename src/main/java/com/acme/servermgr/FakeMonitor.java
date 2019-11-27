package com.acme.servermgr;
/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project
 */
public class ServerManager {

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
     public static String getCurrentServerStatus() {
        return "Server is up";
    }

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
     public Boolean isOperatingNormally()
    {
        return true;
    }
    public static String getOperations(){
         return "is operating normally";
    }
    public static String getMemory(){
         return "memory is running low";
    }
    public static String getExtensions(){
         return "[Hypervisor, Kubernetes, RAID-6]";
    }

}
