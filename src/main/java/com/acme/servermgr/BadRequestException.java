package com.acme.servermgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Used whenever a Bad request is made and can be used to display any error message.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="See debug info for more information")
public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){
        System.out.println(msg);
    }
}
