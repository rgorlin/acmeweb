package com.acme.commands;

/**
 * abstract class  for the Command pattern in acme web commands
 * @see DetailedServerStatusCmd
 * @see StatusCommand
 */
public abstract class StatusCommand implements ExecutableWebCommand {
    protected long id;
    protected String template;
    protected String name;

    public StatusCommand(long id, String template, String name) {
        this.id = id;
        this.name = name;
        this.template = template;
    }
}
