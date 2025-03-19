package com.inditex.peoplecore.repository;

import com.inditex.peoplecore.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product entity repository.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
