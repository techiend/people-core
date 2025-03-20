package com.inditex.peoplecore.controller;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.exception.PriceNotFoundException;
import com.inditex.peoplecore.service.PriceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
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
  private PriceService priceService;

  /**
   * Price Controller Constructor.
   * @param priceService Price service.
   */
  public PriceController(PriceService priceService) {
    this.priceService = priceService;
  }

  /**
   * Find all prices.
   *
   * @return {@link ResponseEntity} with List of prices if found.
   */
  @GetMapping("/prices")
  public ResponseEntity<List<PriceResponse>>findAll() throws PriceNotFoundException {
    return ResponseEntity.ok(priceService.findAll());
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
  public ResponseEntity<PriceResponse>findPrice(
      @RequestParam(name = "date") final LocalDateTime date,
      @RequestParam(name = "productId") final Integer productId,
      @RequestParam(name = "brandId") final Integer brandId) throws PriceNotFoundException {
    return ResponseEntity.ok(priceService.findPrice(date, productId, brandId));
  }

}
