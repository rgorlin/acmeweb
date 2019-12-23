package com.acme.commands;

import com.acme.statusmgr.disk.DiskStatus;
import com.acme.statusmgr.beans.StatusResponce;
import com.acme.statusmgr.disk.DiskStatusProxy;
import com.acme.statusmgr.disk.InvalidNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * A command that can be executed by a executor, used with a Disk status server request
 * added a check to make sure that the user enters a name before using this command, and logged the users who do
 * @see DiskStatusProxy
 * @see StatusCommand
 * @see com.acme.executors.SerialExecutor
 */
public class DiskStatusCmd extends StatusCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private DiskStatusProxy result;
    public DiskStatusCmd(long id, String template, String name) {
        super(id, template, name);
    }

    @Override
    public void execute() {
        if(name.equalsIgnoreCase("anonymous")) {
            throw new InvalidNameException();
        }
        result= new DiskStatusProxy(id,String.format(template,name));
        result.setStatusDesc(result.obtainStatusDesc());
        LOGGER.info("User "+name+" was allowed to access disk status information");
    }

    @Override
    public StatusResponce getResult() {
        return result;
    }
}
