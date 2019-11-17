package statusmgr.beans;

import servermgr.*;

import java.util.List;

/**
 * A POJO that represents Server Status and can be used to generate JSON for that status
 */
public class ServerStatus {

    private  long id;
    private String contentHeader;
    private String statusDesc = "Unknown";

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        // Obtain current status of server
        Status Base= new ServerManager();
        this.statusDesc = Base.getCurrentServerStatus();
    }
    public ServerStatus(long id, String contentHeader, List<String> details) {
        this.id = id;
        this.contentHeader = contentHeader;
        Status Base= new ServerManager();
        for (String s: details){
            if(s.equalsIgnoreCase("operations")){
                Base= new Operations(Base);
            }
            else if(s.equalsIgnoreCase("memory")){
                Base = new Memory(Base);
            }
            else if(s.equalsIgnoreCase("extensions")){
                Base = new Extensions(Base);
            }
            else{
                throw  new BadRequestException(s+ " is not a valid details option");
            }
        }
        this.statusDesc = Base.getCurrentServerStatus();
    }

    public ServerStatus() {

    }

    public long getId() {
        return id;
    }

    public String getContentHeader() {

        return contentHeader;
    }


    public String getStatusDesc() {
        return statusDesc;
    }


}
