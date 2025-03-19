package com.inditex.peoplecore.service;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.mapper.PriceMapper;
import com.inditex.peoplecore.repository.PriceRepository;
import com.inditex.peoplecore.repository.entity.Price;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Price related services.
 */
@Service
@Slf4j
public class PriceService {

  /**
   * Price entity repository.
   */
  @Autowired
  private PriceRepository priceRepository;

  /**
   * Find all prices.
   *
   * @return {@link ResponseEntity} with List of prices if found.
   */
  public ResponseEntity<?> findAll() {
    List<Price> prices = priceRepository.findAllWithRelations();
    if (prices.isEmpty()) {
      // No content to response.
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // Response mapped list.
    return new ResponseEntity<>(PriceMapper.map(prices), HttpStatus.OK);
  }

  /**
   * Find all prices.
   *
   * @param date Search date.
   * @param productId Product identifier.
   * @param brandId Brand identifier.
   * @return {@link ResponseEntity} with List of prices if found.
   */
  public ResponseEntity<?> findPrice(LocalDateTime date, Integer productId, Integer brandId) {
    // All search parameters exist, search.
    Optional<Price> optionalPrice = priceRepository.findByDateAndBrandIdAndProductId(date, brandId, productId);
    if (optionalPrice.isPresent()) {
      Price price = optionalPrice.get();
      PriceResponse response = PriceMapper.map(price);
      log.info("Price found => {}", response);
      // Response mapped price.
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // No content to response.
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
