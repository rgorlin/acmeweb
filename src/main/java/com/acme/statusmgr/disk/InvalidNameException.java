package com.acme.statusmgr.disk;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Must give a name in order to run the Disk Command")
public class InvalidNameException extends RuntimeException {
}
