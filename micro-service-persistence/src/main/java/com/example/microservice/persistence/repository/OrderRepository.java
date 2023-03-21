package com.example.microservice.persistence.repository;

import com.example.microservice.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order JPA repository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
