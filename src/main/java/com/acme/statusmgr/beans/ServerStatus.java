package com.acme.statusmgr.beans;


import com.acme.Application;
import com.acme.servermgr.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import statusmgr.decorators.BasicServerStatus;

import java.util.List;

/**
 * A POJO that represents Server Status and can be used to generate JSON for that status
 */
public abstract class ServerStatus {

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

    public ServerStatus() {

    }

    public long getId() {
        return id;
    }

    public String getContentHeader() {

        return contentHeader;
    }


    public abstract String getStatusDesc();


}
