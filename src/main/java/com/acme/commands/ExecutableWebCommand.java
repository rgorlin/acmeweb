package com.acme.commands;

import com.acme.statusmgr.beans.StatusResponce;

/**
 * interface for the Command pattern in acme web commands
 * @see DetailedServerStatusCmd
 * @see StatusCommand
 */
public interface ExecutableWebCommand {
    void execute();
    StatusResponce getResult();
}
