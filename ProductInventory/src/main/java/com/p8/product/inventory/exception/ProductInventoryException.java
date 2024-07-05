package com.p8.product.inventory.exception;

public class ProductInventoryException extends RuntimeException {
	
	private final String errorCode;

    public ProductInventoryException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
