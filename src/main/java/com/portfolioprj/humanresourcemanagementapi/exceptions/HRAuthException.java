package com.portfolioprj.humanresourcemanagementapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class HRAuthException extends RuntimeException {

    public HRAuthException(String errorMessage){
        super();
    }
}
