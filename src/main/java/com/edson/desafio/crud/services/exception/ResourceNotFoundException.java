package com.edson.desafio.crud.services.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
