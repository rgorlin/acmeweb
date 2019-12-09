package com.acme.statusmgr.beans;


import com.acme.Application;
import com.acme.servermgr.ServerManager;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A POJO that represents Server Status and can be used to generate JSON for that status
 */
public abstract class ServerStatus implements StatusResponce {

    private  long id;
    private String contentHeader;
    private String statusDesc;
    protected ServerManager serverManager;

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
        serverManager = (ServerManager) Application.getApplicationContext().getBean("serverManager");
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Override
    public long getId() {
        return id;
    }
    @Override
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getContentHeader() {

        return contentHeader;
    }


    public abstract String obtainStatusDesc();
    @Override
    public String getStatusDesc(){
        return statusDesc;
    }
    public void setStatusDesc(String statusDesc){
        this.statusDesc=statusDesc;
    }


}
