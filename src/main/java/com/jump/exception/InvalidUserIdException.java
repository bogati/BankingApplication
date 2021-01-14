package com.jump.exception;

public class InvalidUserIdException extends RuntimeException{
	public InvalidUserIdException(String errorMessage, Throwable err) {
		super(errorMessage,err);
	}

}
