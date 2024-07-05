package com.p8.product.inventory.exception;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.p8.product.inventory.model.ProductErrorResponse;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalProductInventoryExceptionHandler {
	
	@ExceptionHandler(ProductInventoryException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorResponse> handleProductInventoryException(ProductInventoryException ex,HttpServletRequest request) {
		ProductErrorResponse response = new ProductErrorResponse();
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("Product Inventory Exception");
		response.setTimestamp(Timestamp.from(Instant.now()));
		response.setErrorMessage(ex.getMessage());
		response.setPath(request.getContextPath());
		response.setMethod(request.getMethod());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProductErrorResponse> handleProductNotFoundException(ProductNotFoundException ex,HttpServletRequest request) {
		ProductErrorResponse response = new ProductErrorResponse();
		response.setCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Product Not Found Exception");
		response.setTimestamp(Timestamp.from(Instant.now()));
		response.setErrorMessage(ex.getMessage());
		response.setPath(request.getContextPath());
		response.setMethod(request.getMethod());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorResponse> handleValidationException(MethodArgumentNotValidException ex,HttpServletRequest request) {
		ProductErrorResponse response = new ProductErrorResponse();
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("Product Inventory Validation Exception");
		response.setTimestamp(Timestamp.from(Instant.now()));
		response.setErrorMessages(ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList()));
		response.setPath(request.getContextPath());
		response.setMethod(request.getMethod());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex,HttpServletRequest request) {
		ProductErrorResponse response = new ProductErrorResponse();
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("Product Inventory Database Integrity Violation Exception");
		response.setTimestamp(Timestamp.from(Instant.now()));
		response.setErrorMessage(ex.getMostSpecificCause().toString());
		response.setPath(request.getContextPath());
		response.setMethod(request.getMethod());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
		
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProductErrorResponse> handleException(Exception ex,HttpServletRequest request) {
		ProductErrorResponse response = new ProductErrorResponse();
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("Product Inventory Generic Exception");
		response.setTimestamp(Timestamp.from(Instant.now()));
		response.setErrorMessage(ex.getMessage());
		response.setPath(request.getContextPath());
		response.setMethod(request.getMethod());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
