package com.inditex.peoplecore.repository;

import com.inditex.peoplecore.repository.entity.Price;
import com.inditex.peoplecore.repository.entity.pk.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, PriceId> {
}
