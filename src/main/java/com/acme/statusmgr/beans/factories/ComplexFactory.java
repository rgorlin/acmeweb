package com.acme.statusmgr.beans.factories;

import com.acme.servermgr.BadRequestException;
import com.acme.statusmgr.beans.ServerStatus;
import org.springframework.stereotype.Component;
import com.acme.decorators.complex.BasicServerStatus;
import com.acme.decorators.complex.Extensions;
import com.acme.decorators.complex.Memory;
import com.acme.decorators.complex.Operations;

import java.util.List;

@Component
public class ComplexFactory implements StatusFactory {
    private long id;
    private String header;
    private List<String> details;

    @Override
    public ServerStatus getServerStatus(long id, String header, List<String> details) {

        this.id = id;
        this.header = header;
        this.details = details;

        ServerStatus base = new BasicServerStatus(id, header);

        ServerStatus decoratedBase = decorate(base);

        return new ServerStatus(id, header) {
            @Override
            public String getStatusDesc() {
                return decoratedBase.getStatusDesc();
            }
        };
    }

    private ServerStatus decorate(ServerStatus base) {
        for (String s : details) {
            if (s.equalsIgnoreCase("operations"))
                base = new Operations(id, header, base);

            else if (s.equalsIgnoreCase("memory"))
                base = new Memory(id, header, base);

            else if (s.equalsIgnoreCase("extensions"))
                base = new Extensions(id, header, base);

            else
                throw new BadRequestException(s + " is not a valid details option");

        }
        return base;
    }
}
