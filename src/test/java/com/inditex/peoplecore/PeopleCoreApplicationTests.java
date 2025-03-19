package com.inditex.peoplecore;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PeopleCoreApplicationTests {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    // Configura la URL base del servicio
    RestAssured.baseURI = "http://localhost:" + port;
  }

  /**
   * Prueba 1: Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
   */
  @Test
  void testPriceAt10Day14() {
    given()
        .param("date", "2020-06-14T10:00:00")
        .param("productId", 35455)
        .param("brandId", 1)
        .when()
        .get("/price")
        .then()
        .statusCode(200)
        .body("productId", equalTo(35455))
        .body("brandId", equalTo(1))
        .body("price", equalTo(35.5F));
  }

  /**
   * Prueba 2: Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
   */
  @Test
  void testPriceAt16Day14() {
    given()
        .param("date", "2020-06-14T16:00:00")
        .param("productId", 35455)
        .param("brandId", 1)
        .when()
        .get("/price")
        .then()
        .statusCode(200)
        .body("productId", equalTo(35455))
        .body("brandId", equalTo(1))
        .body("price", equalTo(25.45F));
  }

  /**
   * Prueba 3: Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
   */
  @Test
  void testPriceAt21Day14() {
    given()
        .param("date", "2020-06-14T21:00:00")
        .param("productId", 35455)
        .param("brandId", 1)
        .when()
        .get("/price")
        .then()
        .statusCode(200)
        .body("productId", equalTo(35455))
        .body("brandId", equalTo(1))
        .body("price", equalTo(35.5F));
  }

  /**
   * Prueba 4: Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA).
   */
  @Test
  void testPriceAt10Day15() {
    given()
        .param("date", "2020-06-15T10:00:00")
        .param("productId", 35455)
        .param("brandId", 1)
        .when()
        .get("/price")
        .then()
        .statusCode(200)
        .body("productId", equalTo(35455))
        .body("brandId", equalTo(1))
        .body("price", equalTo(30.5F));
  }

  /**
   * Prueba 5: Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA).
   */
  @Test
  void testPriceAt21Day15() {
    given()
        .param("date", "2020-06-15T21:00:00")
        .param("productId", 35455)
        .param("brandId", 1)
        .when()
        .get("/price")
        .then()
        .statusCode(200)
        .body("productId", equalTo(35455))
        .body("brandId", equalTo(1))
        .body("price", equalTo(38.95F));
  }

  /**
   * Realizar una peticion para un producto o marca no existente.
   */
  @Test
  void testPriceNotFound() {
    given()
        .param("date", "2020-06-15T21:00:00")
        .param("productId", 123942)
        .param("brandId", 97462)
        .when()
        .get("/price")
        .then()
        .statusCode(404);
  }

  /**
   * Realizar una peticion de todos los precios existentes insertados por la migracion.
   */
  @Test
  void testAllPrices() {
    given()
        .when()
        .get("/prices")
        .then()
        .statusCode(200);
  }

}
