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

/**
 * Order entity
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "product_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_user_id")
    @NotBlank(message = "Order user id is mandatory")
    private Long userId;

    @Column(name = "order_product_id")
    @NotBlank(message = "Order product id is mandatory")
    private Long productId;

    @Column(name = "order_quantity")
    @NotBlank(message = "Order quantity is mandatory")
    private Long quantity;

    @Column(name = "order_create_ts", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long createTs;

    @Column(name = "order_update_ts", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long updateTs;
}
