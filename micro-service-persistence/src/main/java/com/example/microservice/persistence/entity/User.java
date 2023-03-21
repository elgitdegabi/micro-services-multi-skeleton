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
 * User entity
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    @NotBlank(message = "User name is mandatory")
    private String name;

    @Column(name = "user_alias")
    @NotBlank(message = "User alias is mandatory")
    private String alias;

    @Column(name = "user_address")
    @NotBlank(message = "User address is mandatory")
    private String address;

    @Column(name = "user_create_ts", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long createTs;

    @Column(name = "user_update_ts", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long updateTs;
}
