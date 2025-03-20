package com.inditex.peoplecore.repository.entity;

import com.inditex.peoplecore.repository.entity.pk.PriceId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Price entity.
 */
@Data
@Entity(name = "PRICES")
@IdClass(PriceId.class)
public class Price {

  /**
   * Associated Product identifier.
   */
  @Id
  @Column(name = "PRODUCT_ID")
  private Integer product;

  /**
   * Associated Brand identifier.
   */
  @Id
  @Column(name = "BRAND_ID")
  private Integer brand;

  /**
   * Price start date time.
   */
  @Id
  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  /**
   * Price end date time.
   */
  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  /**
   * Price fee identifier.
   */
  @Column(name = "PRICE_LIST")
  private Integer priceList;

  /**
   * Price priority.
   */
  @Column(name = "PRIORITY", columnDefinition = "DEFAULT 0")
  private Integer priority;

  /**
   * Price value.
   */
  @Column(name = "PRICE")
  private Float value;

  /**
   * Price currency.
   */
  @Column(name = "CURR")
  private String currency;

}
