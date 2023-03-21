package com.example.microservice.persistence.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Product entity
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    @NotBlank(message = "Product name is mandatory")
    private String name;

    @Column(name = "product_description")
    @NotBlank(message = "Product description is mandatory")
    private String description;

    @Column(name = "product_quantity")
    @NotBlank(message = "Product quantity is mandatory")
    private Long quantity;

    @Column(name = "product_price")
    @NotBlank(message = "Product price is mandatory")
    private BigDecimal price;

    @Column(name = "product_create_ts", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long createTs;

    @Column(name = "product_update_ts", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long updateTs;
}
