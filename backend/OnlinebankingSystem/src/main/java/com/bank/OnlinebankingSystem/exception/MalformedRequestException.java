package com.bank.OnlinebankingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MalformedRequestException extends Exception {
	public MalformedRequestException(String message){
        super(message);
    }
}
