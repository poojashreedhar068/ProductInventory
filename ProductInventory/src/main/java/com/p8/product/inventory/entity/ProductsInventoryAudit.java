package com.p8.product.inventory.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productsInventoryAudit")
@Getter
@Setter
@NoArgsConstructor
public class ProductsInventoryAudit {
	
	@Id
	@SequenceGenerator(
            name = "productinventoryaudit_sequence",
            sequenceName = "productinventoryaudit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productinventoryaudit_sequence"
    )
	@Schema(name = "Product Inventory Audit ID", example = "1")
	private int id;
	
	@Column(name="productid")
	@Schema(name = "Product Id", example = "1")
	private int productid;
	
	@Column(name="price")
	@Schema(name = "Product price", example = "100.00")
	private double price;
	
	@Column(name="quantity")
	@Schema(name = "Product quantity", example = "10")
	private String quantity;
	
	@Column(name="auditdescription")
	@Schema(name = "Product audit description", example = "New Product is added")
	private String auditdescription;
	
	@UpdateTimestamp
	@Schema(name = "Product Inventory updated timestamp")
	private Timestamp updatedTimeStamp;
	

}
