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
 * Product entity.
 */
@Data
@Entity(name = "PRODUCT")
public class Product {

  /**
   * Product identifier.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
  @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
  @Column(name = "ID")
  private Integer id;

  /**
   * Product name.
   */
  @Column(name = "NAME")
  private String name;

  /**
   * List of associated product prices.
   */
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Price> prices;

}
