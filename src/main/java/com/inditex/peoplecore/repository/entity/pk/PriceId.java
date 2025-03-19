package com.inditex.peoplecore.repository.entity.pk;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Composed primary key for Price entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PriceId implements Serializable {

  /**
   * Price start date time.
   */
  private LocalDateTime startDate;

  /**
   * Price associated product identifier.
   */
  private Integer product;

  /**
   * Price associated brand identifier.
   */
  private Integer brand;

}
