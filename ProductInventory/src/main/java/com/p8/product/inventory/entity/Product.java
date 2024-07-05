package com.p8.product.inventory.entity;


import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products",uniqueConstraints = { @UniqueConstraint(columnNames = { "name"}) })
@Getter
@Setter
@NoArgsConstructor
public class Product {
	
	
	@Id
	@SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
	@Schema(name = "Product ID", example = "1")
	private int id;
	
	@Column(name="name")
	@Schema(name = "Product name", example = "Product 1")
	@NotNull(message="name cannot be null")
	private String name;
	
	@Column(name="description") 
	@Schema(name = "Product description", example = "This is Product 1 used for accessing computers")
	private String description;
	
	@Column(name="price")
	@Schema(name = "Product price", example = "100.00")
	@NotNull(message="price cannot be null")
	@DecimalMin(value = "0.1", inclusive = true, message="product price must be initiated to greater than 0.1")
	private double price;
	
	@UpdateTimestamp
	@Schema(name = "Product updated timestamp")
	private Timestamp updatedTimeStamp;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productinventory_id", referencedColumnName = "id")
	@NotNull(message="product inventory details to be provided")
	@Valid
	private ProductsInventory productsinventory;
	

	
	
	

}
