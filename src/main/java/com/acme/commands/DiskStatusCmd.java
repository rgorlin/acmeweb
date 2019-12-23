package com.acme.commands;

import com.acme.statusmgr.disk.DiskStatus;
import com.acme.statusmgr.beans.StatusResponce;
import com.acme.statusmgr.disk.DiskStatusProxy;

/**
 * A command that can be executed by a executor, used with a Disk status server request
 * @see StatusCommand
 * @see com.acme.executors.SerialExecutor
 */
public class DiskStatusCmd extends StatusCommand {
    private DiskStatusProxy result;
    public DiskStatusCmd(long id, String template, String name) {
        super(id, template, name);
    }

    @Override
    public void execute() {
        result= new DiskStatusProxy(id,String.format(template,name));
        result.setStatusDesc(result.obtainStatusDesc());
    }

    @Override
    public StatusResponce getResult() {
        return result;
    }
}
