package com.acme.executors;

import com.acme.commands.ExecutableWebCommand;

public class SerialExecutor {
    ExecutableWebCommand cmd;
    public  SerialExecutor(ExecutableWebCommand cmd){
        this.cmd=cmd;
    }
    public void handleImmediately(){
        cmd.execute();
    }
}
