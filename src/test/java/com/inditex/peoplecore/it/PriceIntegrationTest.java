package com.inditex.peoplecore.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.inditex.peoplecore.repository.PriceRepository;
import com.inditex.peoplecore.repository.entity.Price;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PriceIntegrationTest {

  @Autowired
  private PriceRepository priceRepository;

  @Test
  void testFindPriceByDateAndBrandAndProduct() {
    Integer productId = 95001;
    Integer brandId = 100;

    Price priceLowPriority = new Price();
    priceLowPriority.setProduct(productId);
    priceLowPriority.setBrand(brandId);
    priceLowPriority.setStartDate(LocalDateTime.of(2025, 1, 1, 0, 0));
    priceLowPriority.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));
    priceLowPriority.setPriceList(1);
    priceLowPriority.setPriority(0);
    priceLowPriority.setValue(25.00f);
    priceLowPriority.setCurrency("EUR");

    Price priceHighPriority = new Price();
    priceHighPriority.setProduct(productId);
    priceHighPriority.setBrand(brandId);
    priceHighPriority.setStartDate(LocalDateTime.of(2025, 6, 1, 0, 0));
    priceHighPriority.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));
    priceHighPriority.setPriceList(2);
    priceHighPriority.setPriority(1);
    priceHighPriority.setValue(30.00f);
    priceHighPriority.setCurrency("EUR");

    priceRepository.saveAll(List.of(priceLowPriority, priceHighPriority));

    LocalDateTime date = LocalDateTime.of(2025, 7, 1, 12, 0);
    Price activePrice = priceRepository.findByDateAndBrandIdAndProductId(date, brandId, productId).orElse(null);

    assertNotNull(activePrice);
    assertEquals(30.00f, activePrice.getValue());
    assertEquals(1, activePrice.getPriority());
  }
}