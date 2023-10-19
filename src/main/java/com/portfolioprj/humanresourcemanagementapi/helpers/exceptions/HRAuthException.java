package com.portfolioprj.humanresourcemanagementapi.helpers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class HRAuthException extends RuntimeException {

    public HRAuthException(String errorMessage){
        super(errorMessage);
    }
}
