package com.acme.statusmgr.disk;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class that handles the exception when a disk Status has not yet been run during the session.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Instant Disk Status is not available please wait for a Disk Status to run ")
public class DiskStatusUnavailableException extends RuntimeException { }