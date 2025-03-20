package com.inditex.peoplecore.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.mapper.PriceMapper;
import com.inditex.peoplecore.repository.entity.Price;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PriceMapperTest {

  @Test
  void testPrivateConstructorThrowsException() throws NoSuchMethodException {
    // Dado: El constructor privado de PriceMapper
    Constructor<PriceMapper> constructor = PriceMapper.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    // Cuando: Intento instanciar y espero una excepción envuelta
    InvocationTargetException exception = assertThrows(
        InvocationTargetException.class,
        constructor::newInstance,
        "Debe lanzar una excepción envuelta en InvocationTargetException"
    );

    // Entonces: Verifico que la causa raíz es IllegalStateException
    Throwable cause = exception.getCause();
    assertEquals(IllegalStateException.class, cause.getClass(), "La causa debe ser IllegalStateException");
    assertEquals("Utility mapper class", cause.getMessage(), "El mensaje de la excepción debe coincidir");
  }

  @Test
  void testMapSinglePrice() {
    Integer productId = 35455;
    Integer brandId = 1;

    Price price = new Price();
    price.setBrand(brandId);
    price.setProduct(productId);
    price.setPriceList(1);
    price.setValue(35.50f);
    price.setStartDate(LocalDateTime.of(2025, 1, 1, 0, 0));
    price.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));

    PriceResponse response = PriceMapper.map(price);

    assertNotNull(response);
    assertEquals(1, response.getBrandId());
    assertEquals(35455, response.getProductId());
    assertEquals(35.50f, response.getPrice());
    assertEquals(1, response.getPriceList());
    assertEquals("01/01/2025 00:00:00", response.getStartDate());
    assertEquals("31/12/2025 23:59:00", response.getEndDate());
  }

  @Test
  void testMapPriceList() {
    Integer productId = 35455;
    Integer brandId = 1;

    Price price1 = new Price();
    price1.setBrand(brandId);
    price1.setProduct(productId);
    price1.setPriceList(1);
    price1.setValue(35.50f);
    price1.setStartDate(LocalDateTime.of(2025, 1, 1, 0, 0));
    price1.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));

    Price price2 = new Price();
    price2.setBrand(brandId);
    price2.setProduct(productId);
    price2.setPriceList(2);
    price2.setValue(40.00f);
    price2.setStartDate(LocalDateTime.of(2025, 6, 1, 0, 0));
    price2.setEndDate(LocalDateTime.of(2025, 12, 31, 23, 59));

    List<Price> prices = Arrays.asList(price1, price2, null);

    List<PriceResponse> responses = PriceMapper.map(prices);

    assertEquals(2, responses.size());
    assertEquals(35.50f, responses.get(0).getPrice());
    assertEquals(40.00f, responses.get(1).getPrice());
    assertEquals("01/01/2025 00:00:00", responses.get(0).getStartDate());
    assertEquals("01/06/2025 00:00:00", responses.get(1).getStartDate());
  }

}
