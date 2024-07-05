package com.p8.product.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="P8 Product Inventory",
description="P8 Product Inventory is the project for Product Inventory Management with required endpoints to maintain inventory designed and developed by Pooja Shreedhar"))
public class ProductInventory {

	public static void main(String[] args) {
		SpringApplication.run(ProductInventory.class, args);
	}

}


