package com.inditex.peoplecore.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.inditex.peoplecore.repository.BrandRepository;
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
class BrandIntegrationTest {

  @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private ProductRepository productRepository;

  @Test
  void testPersistBrandWithPrices() {
    // Dado: Una brand y un producto con precio
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

    // Cuando: Guardo el brand
    Brand savedBrand = brandRepository.save(brand);

    // Entonces: Verifico que se guardó con sus precios
    assertNotNull(savedBrand.getId());
    assertEquals(1, savedBrand.getPrices().size());
    assertEquals("Zara", savedBrand.getName());
    assertEquals(product.getId(), savedBrand.getPrices().get(0).getProduct().getId());
  }
}