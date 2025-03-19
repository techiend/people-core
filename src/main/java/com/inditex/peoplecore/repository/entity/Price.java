package com.inditex.peoplecore.repository.entity;

import com.inditex.peoplecore.repository.entity.pk.PriceId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity(name = "PRICES")
@IdClass(PriceId.class)
public class Price {

  @Id
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
  @JoinColumn(name = "BRAND_ID")
  private Brand brand;

  @Id
  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  @Column(name = "PRICE_LIST")
  private Integer priceList;

  @Column(name = "PRIORITY", columnDefinition = "DEFAULT 0")
  private Integer priority;

  @Column(name = "PRICE")
  private Float price;

  @Column(name = "CURR")
  private String currency;

}
