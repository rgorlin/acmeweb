package com.acme.servermgr;

/**
 * the interface for the Status Decorator.
 * @see IMonitorableServerDecorator
 */
public interface IMonitorableServer {
    String determineServerStatus();
}
