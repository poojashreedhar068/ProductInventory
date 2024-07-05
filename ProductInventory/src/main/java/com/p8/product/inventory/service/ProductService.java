package com.p8.product.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p8.product.inventory.entity.Product;
import com.p8.product.inventory.entity.ProductsInventoryAudit;
import com.p8.product.inventory.exception.ProductNotFoundException;
import com.p8.product.inventory.repository.ProductInventoryAuditRepository;
import com.p8.product.inventory.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService implements IProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductInventoryAuditRepository productInventoryAuditRepository;

	@Override
	public List<Product> getProducts() {

		List<Product> listOfProducts = productRepository.findAll();

		if (listOfProducts.isEmpty()) {
			log.info("Product list is empty");
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND", "Products are empty");

		} else {
			log.info("Product list is fetched");
			return listOfProducts;
		}

	}

	@Override
	public Product postProduct(Product product) {

		Product newproduct = productRepository.save(product);
		
		ProductsInventoryAudit paymentsInventoryAudit = new ProductsInventoryAudit();
		paymentsInventoryAudit.setProductid(newproduct.getId());
		paymentsInventoryAudit.setPrice(newproduct.getPrice());
		paymentsInventoryAudit.setQuantity(newproduct.getProductsinventory().getQuantity());
		paymentsInventoryAudit.setAuditdescription("New Product is added to inventory");
		
		productInventoryAuditRepository.save(paymentsInventoryAudit);
		
		return product;

	}

	@Override
	public Product getProductById(int id) {

		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
			log.info("Product is not existing");
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND", "Product is not existing with id ::" + id);

		} else {
			log.info("Product is fetched");
			return product.get();
		}

	}

	@Override
	public Product updateProductPriceById(double updatedPrice, int id) {

		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
			log.info("Product is not existing");
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND", "Product is not existing with id ::" + id);

		} else {
			product.get().setPrice(updatedPrice);

			productRepository.save(product.get());
			
			ProductsInventoryAudit paymentsInventoryAudit = new ProductsInventoryAudit();
			
			paymentsInventoryAudit.setProductid(product.get().getId());
			paymentsInventoryAudit.setPrice(updatedPrice);
			paymentsInventoryAudit.setAuditdescription("Price on the product is updated");
			paymentsInventoryAudit.setQuantity(product.get().getProductsinventory().getQuantity());
			productInventoryAuditRepository.save(paymentsInventoryAudit);
			
			return product.get();
		}

	}

	@Override
	public Product updateProductQuantityById(String updatedQuantity, int id) {

		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
			log.info("Product is not existing");
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND", "Product is not existing with id ::" + id);

		} else {
			product.get().getProductsinventory().setQuantity(updatedQuantity);
			productRepository.save(product.get());
			
			ProductsInventoryAudit paymentsInventoryAudit = new ProductsInventoryAudit();
			paymentsInventoryAudit.setProductid(product.get().getId());
			paymentsInventoryAudit.setQuantity(updatedQuantity);
			paymentsInventoryAudit.setAuditdescription("Quantity on the product is updated");
			paymentsInventoryAudit.setPrice(product.get().getPrice());
			
			productInventoryAuditRepository.save(paymentsInventoryAudit);
			return product.get();
		}

	}

	@Override
	public Product deleteProductById(int id) {

		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
			log.info("Product is not existing");
			throw new ProductNotFoundException("PRODUCT_NOT_FOUND", "Product is not existing with id ::" + id);

		} else {
			log.info("Product is fetched");

			productRepository.delete(product.get());
			return product.get();
		}

	}
	
}
