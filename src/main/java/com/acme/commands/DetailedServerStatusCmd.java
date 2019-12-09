package com.acme.commands;

import com.acme.servermgr.BadRequestException;
import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.beans.StatusResponce;
import com.acme.statusmgr.beans.factories.ComplexFactory;
import com.acme.statusmgr.beans.factories.SimpleFactory;
import com.acme.statusmgr.beans.factories.StatusFactory;

import java.util.List;

/**
 * A command that can be executed by a executor, used with a detailed server request
 * @see StatusCommand
 * @see com.acme.executors.SerialExecutor
 */
public class DetailedServerStatusCmd extends StatusCommand {
    private List<String> details;
    private ServerStatus result;
    private StatusFactory factory;
    public DetailedServerStatusCmd(long id, String template, String name, List<String> details,String levelOfDetail) {
        super(id, template, name);
        this.details=details;
        determineFactory(levelOfDetail);
    }

    private void determineFactory(String levelOfDetail) {
        if (levelOfDetail.equalsIgnoreCase("complex"))
             factory= new ComplexFactory();

        else if (levelOfDetail.equalsIgnoreCase("simple")) {
            factory = new SimpleFactory();
        } else {
            throw new BadRequestException(levelOfDetail + "is not a valid levelOfDetail");
        }
    }

    @Override
    public void execute() {
        result=factory.getServerStatus(this.id,String.format(template,name),details);
        result.setStatusDesc(result.obtainStatusDesc());
    }

    @Override
    public StatusResponce getResult() {
        return result;
    }
}
