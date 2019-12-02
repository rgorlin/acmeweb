package com.acme.statusmgr.beans.factories;

import com.acme.statusmgr.beans.ServerStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StatusFactory {
    ServerStatus getServerStatus(long id, String header, List<String> details);
}
