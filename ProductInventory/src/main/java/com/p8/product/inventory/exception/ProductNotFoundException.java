package com.p8.product.inventory.exception;

public class ProductNotFoundException extends RuntimeException {
	
	private final String errorCode;

    public ProductNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
