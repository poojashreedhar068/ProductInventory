package com.p8.product.inventory.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UrlPathHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.p8.product.inventory.entity.Product;
import com.p8.product.inventory.exception.ProductInventoryException;
import com.p8.product.inventory.model.ProductResponse;
import com.p8.product.inventory.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "P8 Product Inventory API's")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ObjectMapper objectMapper;


	@Operation(summary = "Get All Products", description = "Returns All Products details")
	@GetMapping("/products")
	ResponseEntity<ProductResponse> getProducts(HttpServletRequest request) {
		MDC.put("Flow", "GET_ALL_PRODUCTS");
		log.info("Received GetProducts request");
		List<Product> listOfProducts = productService.getProducts();

		ProductResponse<List<Product>> productResponse = new ProductResponse<List<Product>>();
		productResponse.setCode(HttpStatus.OK.value());
		productResponse.setData(listOfProducts);
		productResponse.setMessage("No Of Products Obtained ::" + listOfProducts.size());
		productResponse.setTimestamp(Timestamp.from(Instant.now()));
		productResponse.setPath(request.getContextPath());
		productResponse.setMethod(request.getMethod());
		log.info("Response :: {}",productResponse.toString());

		return ResponseEntity.ok(productResponse);

	}

	@Operation(summary = "Post Product", description = "Add new Product into Product inventory")
	@PostMapping("/products")
	ResponseEntity<ProductResponse> postProduct(@RequestBody @Valid Product product,HttpServletRequest request) {
		MDC.put("Flow", "POST_NEW_PRODUCT");
		log.info("Received PostProduct request");
		log.info("Request received ::{}",product.toString());
		Product responseProduct = productService.postProduct(product);

		ProductResponse productResponse = new ProductResponse();
		productResponse.setCode(HttpStatus.OK.value());
		productResponse.setData(responseProduct);
		productResponse.setMessage("Product has been successfully saved");
		productResponse.setTimestamp(Timestamp.from(Instant.now()));
		productResponse.setPath(request.getContextPath());
		productResponse.setMethod(request.getMethod());
		
		log.info("Response :: {}",productResponse.toString());
		return ResponseEntity.ok(productResponse);

	}

	@Operation(summary = "Get Product By Id", description = "Retrieve Product detail by Id")
	@GetMapping("/products/{id}")
	ResponseEntity<ProductResponse> getProductById(@PathVariable @Positive int id,HttpServletRequest request) {
		MDC.put("Flow", "GET_PRODUCT_BY_ID");
		log.info("Received GetProductById request");
		Product product = productService.getProductById(id);

		ProductResponse productResponse = new ProductResponse();
		productResponse.setCode(HttpStatus.OK.value());
		productResponse.setData(product);
		productResponse.setMessage("Product details are successfully fetched");
		productResponse.setTimestamp(Timestamp.from(Instant.now()));
		productResponse.setPath(request.getContextPath());
		productResponse.setMethod(request.getMethod());
		
		log.info("Response :: {}",productResponse.toString());
		return ResponseEntity.ok(productResponse);

	}

	@Operation(summary = "Update Product By Id", description = "Update Product detail by Id")
	@PutMapping("/products/{id}")
	ResponseEntity<ProductResponse> putProduct(@RequestBody String updateproductstring, @PathVariable int id,HttpServletRequest request) {
		MDC.put("Flow", "UPDATE_PRODUCT_BY_ID");
		log.info("Received PutProduct request");
		log.info("Request received ::{}",updateproductstring);
		JsonObject jobj = new Gson().fromJson(updateproductstring, JsonObject.class);

		Product responseProduct = null;
		
		  if(jobj.has("price")) 
			  responseProduct = productService.updateProductPriceById(jobj.get("price").getAsDouble(), id);
		 
		if (jobj.has("quantity"))
			responseProduct = productService.updateProductQuantityById(jobj.get("quantity").getAsString(), id);

				
		  if ( !jobj.has("price") && !jobj.has("quantity")) 
			  throw new ProductInventoryException("VALIDATION_FAILED","Either Price or Quantity needs to be provided to update the product");
		 
		ProductResponse productResponse = new ProductResponse();
		productResponse.setCode(HttpStatus.OK.value());
		productResponse.setData(responseProduct);
		productResponse.setMessage("Product details are successfully updated");
		productResponse.setTimestamp(Timestamp.from(Instant.now()));
		productResponse.setPath(request.getContextPath());
		productResponse.setMethod(request.getMethod());
		
		log.info("Response :: {}",productResponse.toString());
		return ResponseEntity.ok(productResponse);

	}

	@Operation(summary = "Delete Product By Id", description = "Delete Product by Id from Product Inventory")
	@DeleteMapping("/products/{id}")
	ResponseEntity<ProductResponse> deleteProduct(@PathVariable @Positive int id,HttpServletRequest request) {
		MDC.put("Flow", "DELETE_PRODUCT_BY_ID");
		log.info("Received DeleteProduct request");
		Product product = productService.deleteProductById(id);

		ProductResponse productResponse = new ProductResponse();
		productResponse.setCode(HttpStatus.OK.value());
		productResponse.setData(product);
		productResponse.setMessage("Product details are successfully deleted");
		productResponse.setTimestamp(Timestamp.from(Instant.now()));
		productResponse.setPath(request.getContextPath());
		productResponse.setMethod(request.getMethod());
		
		log.info("Response :: {}",productResponse.toString());
		return ResponseEntity.ok(productResponse);

	}
	

}
