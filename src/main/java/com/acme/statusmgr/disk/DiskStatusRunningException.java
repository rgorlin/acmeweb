package com.acme.statusmgr.disk;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class that handles the exception when a disk Status has not yet been run during the session.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Disk status is already running please wait for it to finish")
public class DiskStatusRunningException extends RuntimeException { }
