package com.inditex.peoplecore.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.inditex.peoplecore.controller.PriceController;
import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.exception.PriceNotFoundException;
import com.inditex.peoplecore.service.PriceService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

  @Mock
  private PriceService priceService;

  @InjectMocks
  private PriceController priceController;

  private PriceResponse samplePrice;

  @BeforeEach
  void setUp() {
    samplePrice = PriceResponse.builder()
        .productId(35455)
        .brandId(1)
        .price(35.5F)
        .build();
  }

  @Test
  void testFindAllSuccess() throws PriceNotFoundException {
    List<PriceResponse> prices = Collections.singletonList(samplePrice);
    when(priceService.findAll()).thenReturn(prices);

    ResponseEntity<List<PriceResponse>> response = priceController.findAll();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(prices, response.getBody());
    assertEquals(1, response.getBody().size());
  }

  @Test
  void testFindAllThrowsPriceNotFoundException() throws PriceNotFoundException {
    when(priceService.findAll()).thenThrow(new PriceNotFoundException("EMPTY_PRICES", "No prices found", HttpStatus.NOT_FOUND));

    PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
      priceController.findAll();
    });

    assertEquals("EMPTY_PRICES", exception.getCode());
    assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
  }

  @Test
  void testFindPriceSuccess() throws PriceNotFoundException {
    LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
    Integer productId = 35455;
    Integer brandId = 1;
    when(priceService.findPrice(date, productId, brandId)).thenReturn(samplePrice);

    ResponseEntity<PriceResponse> response = priceController.findPrice(date, productId, brandId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(samplePrice, response.getBody());
    assertEquals(35455, response.getBody().getProductId());
    assertEquals(1, response.getBody().getBrandId());
    assertEquals(35.5F, response.getBody().getPrice());
  }

  @Test
  void testFindPriceThrowsPriceNotFoundException() throws PriceNotFoundException {
    LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
    Integer productId = 35455;
    Integer brandId = 1;
    when(priceService.findPrice(date, productId, brandId))
        .thenThrow(new PriceNotFoundException("PRICE_NOT_FOUND", "Price not found", HttpStatus.NOT_FOUND));

    PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
      priceController.findPrice(date, productId, brandId);
    });

    assertEquals("PRICE_NOT_FOUND", exception.getCode());
    assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
  }
}