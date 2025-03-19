package com.inditex.peoplecore.repository;

import com.inditex.peoplecore.repository.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Brand entity repository.
 */
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
