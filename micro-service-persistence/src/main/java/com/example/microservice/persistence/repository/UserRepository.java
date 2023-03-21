package com.example.microservice.persistence.repository;

import com.example.microservice.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User JPA repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
