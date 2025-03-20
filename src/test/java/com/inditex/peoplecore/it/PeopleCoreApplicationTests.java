package com.inditex.peoplecore.it;

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
    RestAssured.baseURI = "http://localhost:" + port;
  }

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

  @Test
  void testAllPrices() {
    given()
        .when()
        .get("/prices")
        .then()
        .statusCode(200);
  }

  @Test
  void testFindPriceWithWrongParams() {
    given()
        .param("date", "FAKE_DATE")
        .param("productId", 35455)
        .param("brandId", 1)
        .when()
        .get("/price")
        .then()
        .statusCode(500);
  }

}
