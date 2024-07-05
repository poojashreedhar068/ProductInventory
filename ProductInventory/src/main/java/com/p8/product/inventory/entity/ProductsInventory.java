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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productsinventory")
@Getter
@Setter
@NoArgsConstructor
public class ProductsInventory {
	
	@Id
	@SequenceGenerator(
            name = "productsinventory_sequence",
            sequenceName = "productsinventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productsinventory_sequence"
    )
	@Schema(name = "ProductsInventory ID", example = "1")
	private int id;
	
	
	@Column(name="quantity")
	@Schema(name = "Product quantity", example = "10")
	@NotNull(message="quantity cannot be null")
	private String quantity;
	
	@UpdateTimestamp
	@Schema(name = "Product Inventory updated timestamp")
	private Timestamp updatedTimeStamp;

}
