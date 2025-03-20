package com.inditex.peoplecore.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.exception.ExceptionCodeEnum;
import com.inditex.peoplecore.exception.PriceNotFoundException;
import com.inditex.peoplecore.repository.PriceRepository;
import com.inditex.peoplecore.repository.entity.Brand;
import com.inditex.peoplecore.repository.entity.Price;
import com.inditex.peoplecore.repository.entity.Product;
import com.inditex.peoplecore.service.implementation.PriceServiceImpl;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceServiceImpl priceServiceImp;

  @Test
  void testFindAllWithNoPrices() {
    when(priceRepository.findAllWithRelations()).thenReturn(Collections.emptyList());

    PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
      priceServiceImp.findAll();
    });

    assertEquals(ExceptionCodeEnum.EMPTY_PRICES.name(), exception.getCode());
    assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
  }

  @Test
  void testFindPrice() throws PriceNotFoundException {
    LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
    Integer productId = 35455;
    Integer brandId = 1;

    Product product = new Product();
    product.setId(productId);

    Brand brand = new Brand();
    brand.setId(brandId);

    Price expectedPrice = new Price();
    expectedPrice.setProduct(product);
    expectedPrice.setBrand(brand);
    expectedPrice.setStartDate(date);
    expectedPrice.setEndDate(LocalDateTime.parse("2020-06-14T23:59:59"));
    expectedPrice.setPriceList(1);
    expectedPrice.setPriority(0);
    expectedPrice.setValue(35.5F);
    expectedPrice.setCurrency("EUR");

    when(priceRepository.findByDateAndBrandIdAndProductId(any(LocalDateTime.class), eq(brandId), eq(productId)))
        .thenReturn(Optional.of(expectedPrice));

    PriceResponse actualPrice = priceServiceImp.findPrice(date, productId, brandId);

    assertEquals(productId, actualPrice.getProductId());
    assertEquals(brandId, actualPrice.getBrandId());
    assertEquals(35.5F, actualPrice.getPrice());
  }

  @Test
  void testFindAllPrices() throws PriceNotFoundException {
    LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
    Integer productId = 35455;
    Integer brandId = 1;

    Product product = new Product();
    product.setId(productId);

    Brand brand = new Brand();
    brand.setId(brandId);

    Price expectedPrice1 = new Price();
    expectedPrice1.setProduct(product);
    expectedPrice1.setBrand(brand);
    expectedPrice1.setStartDate(date);
    expectedPrice1.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
    expectedPrice1.setPriceList(1);
    expectedPrice1.setPriority(0);
    expectedPrice1.setValue(35.5F);
    expectedPrice1.setCurrency("EUR");


    Price expectedPrice2 = new Price();
    expectedPrice2.setProduct(product);
    expectedPrice2.setBrand(brand);
    expectedPrice2.setStartDate(date);
    expectedPrice2.setEndDate(LocalDateTime.parse("2020-06-18T23:59:59"));
    expectedPrice2.setPriceList(1);
    expectedPrice2.setPriority(1);
    expectedPrice2.setValue(27.99F);
    expectedPrice2.setCurrency("EUR");

    when(priceRepository.findAllWithRelations())
        .thenReturn(List.of(expectedPrice1, expectedPrice2));

    List<PriceResponse> priceResponseList = priceServiceImp.findAll();

    assertEquals(2, priceResponseList.size());
  }

  @Test
  void testFindPriceForNonExistProduct() {
    LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
    Integer productId = 35455;
    Integer brandId = 1;
    when(priceRepository.findByDateAndBrandIdAndProductId(date, brandId, productId))
        .thenReturn(Optional.empty());

    PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
      priceServiceImp.findPrice(date, productId, brandId);
    });

    assertEquals(ExceptionCodeEnum.PRICE_NOT_FOUND.name(), exception.getCode());
    assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
  }

}
