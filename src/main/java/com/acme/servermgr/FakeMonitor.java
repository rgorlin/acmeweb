package com.acme.servermgr;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

/**
 * Implement a fake server monitor such as would be used for unit tests, annotated as a Spring Service.
 */
@Service
public class FakeMonitor implements IMonitorableServer {

    @Override
    public String getCurrentServerStatus() {
        return "Server is up";
    }
}