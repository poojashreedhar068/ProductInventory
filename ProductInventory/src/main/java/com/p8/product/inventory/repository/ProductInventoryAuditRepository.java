package com.p8.product.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.p8.product.inventory.entity.ProductsInventoryAudit;

@Repository
public interface ProductInventoryAuditRepository extends JpaRepository<ProductsInventoryAudit, Integer> {
	

}
