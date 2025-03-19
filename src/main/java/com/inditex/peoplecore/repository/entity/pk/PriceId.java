package com.inditex.peoplecore.repository.entity.pk;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PriceId implements Serializable {

  private LocalDateTime startDate;
  private Integer product;
  private Integer brand;

}
