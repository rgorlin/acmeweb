package com.acme.statusmgr.disk;

import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.beans.StatusResponce;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A proxy class that handles the communication between the user and the Disk Status class
 * this class allows for the user to have a more friendly experience when using this command
 * @see DiskStatusThread
 * @see DiskStatus
 */
public class DiskStatusProxy implements StatusResponce {
    static String DISK_STATUS_RESULTS="";
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    static LocalDateTime DISK_STATUS_RESULTS_TIME;
    private DiskStatus ds;



    public DiskStatusProxy(long id, String contentHeader){
        ds= new DiskStatus(id,contentHeader);
    }

    @Override
    public long getId() {
        return ds.getId();
    }

    @Override
    public String getContentHeader() {
       return ds.getContentHeader();
    }

    @Override
    public String getStatusDesc() {
        return ds.getStatusDesc();
    }
    public void setStatusDesc(String statusDesc) {
        ds.setStatusDesc(statusDesc);
    }
    public String obtainStatusDesc() {
            DiskStatusThread dst = new DiskStatusThread(ds);
            Thread t = new Thread(dst);
            t.start();
            String result = "";
            if (DISK_STATUS_RESULTS.equals("")) {
                throw new DiskStatusUnavailableException();
            } else {
                result = "please check back in a few minutes for a more up to date version" + System.lineSeparator() +
                        " this version is from " + System.lineSeparator() +
                        DISK_STATUS_RESULTS_TIME.toString() + System.lineSeparator() + DISK_STATUS_RESULTS;
            }
            return result;
    }
}