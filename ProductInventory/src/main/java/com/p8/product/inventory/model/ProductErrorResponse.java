package com.p8.product.inventory.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductErrorResponse<T> extends ProductGenericResponse {
	
	T data;
	String errorMessage;
	List<String> errorMessages;
	String path;
	String method;

}
