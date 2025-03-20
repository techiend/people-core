package com.inditex.peoplecore.mapper;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.repository.entity.Price;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Component used to map {@link Price}
 */
public class PriceMapper {

  /**
   * Default constructor for the class.
   * <p>
   * This constructor initializes an instance without any parameters. It is typically used
   * when no initial configuration is required. Additional setup can be performed via
   * setters or other initialization methods.
   * </p>
   */
  private PriceMapper() {
    // Empty constructor
    throw new IllegalStateException("Utility mapper class");
  }

  /**
   * Map {@link Price} to {@link PriceResponse}.
   *
   * @param price price entity.
   * @return price dto.
   */
  public static PriceResponse map(Price price) {
    return PriceResponse.builder()
        .brandId(price.getBrand())
        .productId(price.getProduct())
        .price(price.getValue())
        .priceList(price.getPriceList())
        .startDate(price.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
        .endDate(price.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
        .build();
  }

  /**
   * Map list of {@link Price} to list of {@link PriceResponse}.
   *
   * @param prices price entity list.
   * @return price dto list.
   */
  public static List<PriceResponse> map(List<Price> prices) {
    List<PriceResponse> responses = new ArrayList<>();
    for (Price price :
        prices) {
      if (Objects.nonNull(price)) {
        responses.add(map(price));
      }
    }
    return responses;
  }
}
