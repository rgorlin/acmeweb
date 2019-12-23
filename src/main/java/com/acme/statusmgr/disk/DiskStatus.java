package com.acme.statusmgr.disk;

import com.acme.statusmgr.beans.StatusResponce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class DiskStatus implements StatusResponce {

    /**
     * The command for windows to interact with the disk
     */
    private static final String[] DISK_COMMAND = {"cmd", "/C", "Dir", "/S", "C:\\*.java"};

    /**
     * The Id number of request
     */
    private long id;
    /**
     * Details about  request
     */
    private String contentHeader;
    /**
     * Output by OS to give back disk info
     */
    private String diskCommandResult;

    private String statusDesc;

    public DiskStatus(long id, String contentHeader){
        this.id = id;
        this.contentHeader = contentHeader;
    }

    @Override
    public long getId() {
        return id;
    }
    @Override
    public String getContentHeader() {
        return contentHeader;
    }
    public String obtainStatusDesc(){
        return getDiskCommandResult();
    }

    private String getDiskCommandResult(){

        Runtime rt = Runtime.getRuntime();

        try {
            Process chkProcess = rt.exec(DISK_COMMAND);

            diskCommandResult = new BufferedReader(new InputStreamReader(chkProcess.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        DiskStatusProxy.DISK_STATUS_RESULTS=diskCommandResult;
        DiskStatusProxy.DISK_STATUS_RESULTS_TIME=LocalDateTime.now();
        System.out.println("Finished running a disk command at " + LocalDateTime.now().toString());
        return diskCommandResult;
    }
    @Override
    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public class DiskStatusThread implements Runnable
    {
        @Override
        public void run()
        {
            obtainStatusDesc();
        }
    }
}
