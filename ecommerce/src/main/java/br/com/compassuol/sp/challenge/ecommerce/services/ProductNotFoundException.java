package br.com.compassuol.sp.challenge.ecommerce.service;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message) {	
		super(message);
	}
	
	public ProductNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
