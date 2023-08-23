package com.bank.OnlinebankingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TransactionFailedToLogException extends Exception {
	public TransactionFailedToLogException(String message) {
		super(message);
	}
}
