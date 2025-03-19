package com.inditex.peoplecore.repository;

import com.inditex.peoplecore.repository.entity.Price;
import com.inditex.peoplecore.repository.entity.pk.PriceId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Price entity repository.
 */
public interface PriceRepository extends JpaRepository<Price, PriceId> {

  /**
   * Find all prices with relations.
   * @return List of price entities.
   */
  @Query("SELECT p FROM PRICES p JOIN FETCH p.product JOIN FETCH p.brand")
  List<Price> findAllWithRelations();

  /**
   * Find all prices with search parameters.
   *
   * @param date Search date.
   * @param brandId Brand identifier.
   * @param productId Product identifier.
   * @return Price found.
   */
  @Query(value = "SELECT p.* FROM PRICES p "
      + "WHERE p.PRODUCT_ID = :productId AND p.BRAND_ID = :brandId "
      + "AND :date BETWEEN p.START_DATE AND p.END_DATE "
      + "ORDER BY p.PRIORITY DESC, p.PRICE DESC LIMIT 1", nativeQuery = true)
  Optional<Price> findByDateAndBrandIdAndProductId(
      @Param("date") LocalDateTime date,
      @Param("brandId") Integer brandId,
      @Param("productId") Integer productId
  );

}
