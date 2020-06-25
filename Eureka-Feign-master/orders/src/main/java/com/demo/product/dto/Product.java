
package com.demo.product.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity

@Table(name = "product")

@Getter

@Data

@Setter

@ToString
public class Product {

	@Column(name = "id")

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")

	@NotNull

	@NotEmpty
	private String name;

	@Column(length = 500)

	@NotNull

	@NotEmpty
	private String description;

	@NotNull
	private boolean availability;

	@NotNull
	private BigDecimal price;

}
