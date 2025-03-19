package com.inditex.peoplecore.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.List;
import lombok.Data;

/**
 * Brand entity.
 */
@Data
@Entity(name = "BRAND")
public class Brand {

  /**
   * Brand identifier.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_id_seq")
  @SequenceGenerator(name = "brand_id_seq", sequenceName = "brand_id_seq", allocationSize = 1)
  @Column(name = "ID")
  private Integer id;

  /**
   * Brand name.
   */
  @Column(name = "NAME")
  private String name;

  /**
   * List of associated brand prices.
   */
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Price> prices;

}
