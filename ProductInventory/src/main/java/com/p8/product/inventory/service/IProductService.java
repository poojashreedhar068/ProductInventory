package com.p8.product.inventory.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.p8.product.inventory.entity.Product;

@Component
public interface IProductService {
	
	List<Product> getProducts();
	Product postProduct(Product product);
	Product getProductById(int id);
	Product updateProductPriceById(double updatedPrice, int id);
	Product updateProductQuantityById(String updatedQuantity, int id);
	Product deleteProductById(int id);
}

