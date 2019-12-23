package com.acme.statusmgr.disk;

/**
 * A class to be able to handle the expensive disk command in the background to allow the user to see old information
 * or to display an error saying info is not yet available
 */
public class DiskStatusThread extends Thread {
    private DiskStatus ds;
    public DiskStatusThread(DiskStatus ds){
        this.ds=ds;
    }

    public void run(){
        ds.obtainStatusDesc();
    }
}
