package com.inditex.peoplecore.service;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.exception.PriceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Price related services interface.
 */
public interface PriceService {

  /**
   * Find all prices.
   *
   * @return List of prices if found.
   */
  List<PriceResponse> findAll() throws PriceNotFoundException;

  /**
   * Find specific price for a Product.
   *
   * @param date Search date.
   * @param productId Product identifier.
   * @param brandId Brand identifier.
   * @return Price if found.
   */
  PriceResponse findPrice(LocalDateTime date, Integer productId, Integer brandId) throws PriceNotFoundException;
}
