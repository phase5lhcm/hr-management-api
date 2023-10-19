package com.portfolioprj.humanresourcemanagementapi.helpers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HRDeptResourceNotFoundException extends RuntimeException{

    public HRDeptResourceNotFoundException(String message){
        super(message);
    }
}
