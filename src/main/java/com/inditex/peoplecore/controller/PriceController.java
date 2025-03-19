package com.inditex.peoplecore.controller;

import com.inditex.peoplecore.service.PriceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link com.inditex.peoplecore.repository.entity.Price} controller.
 */
@Tag(name = "Price API", description = "This API handle all price management functionality")
@RestController
@RequestMapping()
public class PriceController {

  /**
   * Price service.
   */
  @Autowired
  private PriceService priceService;

  /**
   * Find all prices.
   *
   * @return {@link ResponseEntity} with List of prices if found.
   */
  @GetMapping("/prices")
  public ResponseEntity<?>findAll() {
    return priceService.findAll();
  }

  /**
   * Find price.
   *
   * @param date Search date.
   * @param productId Product identifier.
   * @param brandId Brand identifier.
   * @return {@link ResponseEntity} with List of prices if found.
   */
  @GetMapping("/price")
  public ResponseEntity<?>findPrice(
      @RequestParam(name = "date") final LocalDateTime date,
      @RequestParam(name = "productId") final Integer productId,
      @RequestParam(name = "brandId") final Integer brandId) {
    return priceService.findPrice(date, productId, brandId);
  }

}
