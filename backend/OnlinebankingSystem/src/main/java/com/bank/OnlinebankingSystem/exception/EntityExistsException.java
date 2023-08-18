package com.bank.OnlinebankingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntityExistsException extends Exception {
	public EntityExistsException(String message){
        super(message);
    }
}
