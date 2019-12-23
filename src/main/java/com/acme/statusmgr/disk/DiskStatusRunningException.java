package com.acme.statusmgr.disk;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class that handles the exception when a Disk Status is already running.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Disk status is already running please wait for it to finish")
public class DiskStatusRunningException extends RuntimeException { }
