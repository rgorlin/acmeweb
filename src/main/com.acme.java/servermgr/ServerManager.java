package servermgr;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project
 */
public class ServerManager implements Status {

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
     public String getCurrentServerStatus() {
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
}
