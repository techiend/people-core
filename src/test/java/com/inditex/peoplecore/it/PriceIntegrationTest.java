package com.inditex.peoplecore.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.inditex.peoplecore.repository.BrandRepository;
import com.inditex.peoplecore.repository.PriceRepository;
import com.inditex.peoplecore.repository.ProductRepository;
import com.inditex.peoplecore.repository.entity.Brand;
import com.inditex.peoplecore.repository.entity.Price;
import com.inditex.peoplecore.repository.entity.Product;
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
  private BrandRepository brandRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private PriceRepository priceRepository;

  @Test
  void testPersistPrices() {
    Product product = new Product();
    product.setName("Camiseta");
    productRepository.save(product);

    Brand brand = new Brand();
    brand.setName("Zara");

    Price price = new Price();
    price.setProduct(product);
    price.setBrand(brand);
    price.setStartDate(LocalDateTime.of(2025, 1, 1, 0, 0));
    price.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));
    price.setPriceList(1);
    price.setPriority(1);
    price.setValue(35.50f);
    price.setCurrency("EUR");

    brand.setPrices(List.of(price));

    Brand savedBrand = brandRepository.save(brand);

    assertNotNull(savedBrand.getId());
    assertEquals(1, savedBrand.getPrices().size());
    assertEquals("Zara", savedBrand.getName());
    assertEquals(product.getId(), savedBrand.getPrices().get(0).getProduct().getId());
  }

  @Test
  void testFindPriceByDateAndBrandAndProduct() {
    Product product = new Product();
    product.setName("Pantalón");
    productRepository.save(product);

    Brand brand = new Brand();
    brand.setName("Pull&Bear");
    brandRepository.save(brand);

    Price priceLowPriority = new Price();
    priceLowPriority.setProduct(product);
    priceLowPriority.setBrand(brand);
    priceLowPriority.setStartDate(LocalDateTime.of(2025, 1, 1, 0, 0));
    priceLowPriority.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));
    priceLowPriority.setPriceList(1);
    priceLowPriority.setPriority(0);
    priceLowPriority.setValue(25.00f);
    priceLowPriority.setCurrency("EUR");

    Price priceHighPriority = new Price();
    priceHighPriority.setProduct(product);
    priceHighPriority.setBrand(brand);
    priceHighPriority.setStartDate(LocalDateTime.of(2025, 6, 1, 0, 0));
    priceHighPriority.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));
    priceHighPriority.setPriceList(2);
    priceHighPriority.setPriority(1);
    priceHighPriority.setValue(30.00f);
    priceHighPriority.setCurrency("EUR");

    priceRepository.saveAll(List.of(priceLowPriority, priceHighPriority));

    LocalDateTime date = LocalDateTime.of(2025, 7, 1, 12, 0);
    Price activePrice = priceRepository.findByDateAndBrandIdAndProductId(date, brand.getId(), product.getId()).orElse(null);

    assertNotNull(activePrice);
    assertEquals(30.00f, activePrice.getValue());
    assertEquals(1, activePrice.getPriority());
  }
}