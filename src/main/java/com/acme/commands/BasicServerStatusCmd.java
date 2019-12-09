package com.acme.commands;

import com.acme.decorators.complex.BasicServerStatus;
import com.acme.statusmgr.beans.StatusResponce;

/**
 * A command that can be executed by a executor, used with a simple server status request
 * @see StatusCommand
 * @see com.acme.executors.SerialExecutor
 */
public class BasicServerStatusCmd extends StatusCommand {
    private BasicServerStatus status;
    public BasicServerStatusCmd(long id, String template, String name) {
        super(id, template, name);
    }

    @Override
    public void execute() {
        status = new BasicServerStatus(id, String.format(template, name));
        status.setStatusDesc(status.obtainStatusDesc());
    }

    @Override
    public StatusResponce getResult() {
        return status;
    }


}
