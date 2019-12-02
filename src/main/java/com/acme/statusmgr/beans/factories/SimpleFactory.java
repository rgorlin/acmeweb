package com.acme.statusmgr.beans.factories;

import com.acme.servermgr.BadRequestException;
import com.acme.statusmgr.beans.ServerStatus;
import com.acme.decorators.simple.SimpleBasicServerStatus;
import com.acme.decorators.simple.SimpleExtensions;
import com.acme.decorators.simple.SimpleMemory;
import com.acme.decorators.simple.SimpleOperations;

import java.util.List;

public class SimpleFactory implements StatusFactory {
    private long id;
    private String header;
    private List<String> details;

    @Override
    public ServerStatus getServerStatus(long id, String header, List<String> details) {

        this.id = id;
        this.header = header;
        this.details = details;

        ServerStatus base = new SimpleBasicServerStatus(id, header);

        ServerStatus decoratedBase = decorateBaseComp(base);

        return new ServerStatus(id, header) {
            @Override
            public String getStatusDesc() {
                return decoratedBase.getStatusDesc();
            }
        };

    }

    private ServerStatus decorateBaseComp(ServerStatus base) {
        for (String s : details) {
            if (s.equalsIgnoreCase("operations"))
                base = new SimpleOperations(id, header, base);

            else if (s.equalsIgnoreCase("memory"))
                base = new SimpleMemory(id, header, base);

            else if (s.equalsIgnoreCase("extensions"))
                base = new SimpleExtensions(id, header, base);

            else
                throw new BadRequestException("Invalid details option: " + s);

        }
        return base;
    }
}
