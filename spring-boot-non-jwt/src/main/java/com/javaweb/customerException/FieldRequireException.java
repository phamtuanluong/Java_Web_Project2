package com.javaweb.customerException;


public class FieldRequireException extends RuntimeException{
	public FieldRequireException(String s) {
		super(s);
	}
}