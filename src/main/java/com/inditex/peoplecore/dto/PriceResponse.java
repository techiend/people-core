package com.inditex.peoplecore.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Price data transfer object.
 */
@Data
@Builder
public class PriceResponse {

  /**
   * Product identifier.
   */
  private Integer productId;

  /**
   * Brand identifier.
   */
  private Integer brandId;

  /**
   * Price fee identifier.
   */
  private Integer priceList;

  /**
   * Price start date time.
   */
  private String startDate;

  /**
   * Price end date time.
   */
  private String endDate;

  /**
   * Price value.
   */
  private Float price;
}
