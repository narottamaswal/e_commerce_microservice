package com.demo.product.exception;

public class InsufficientProductQuantityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public InsufficientProductQuantityException(String message) {
        super(message);
    }
}
