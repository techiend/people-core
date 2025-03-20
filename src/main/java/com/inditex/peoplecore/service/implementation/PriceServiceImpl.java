package com.inditex.peoplecore.service.implementation;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.exception.ExceptionCodeEnum;
import com.inditex.peoplecore.exception.PriceNotFoundException;
import com.inditex.peoplecore.mapper.PriceMapper;
import com.inditex.peoplecore.repository.PriceRepository;
import com.inditex.peoplecore.repository.entity.Price;
import com.inditex.peoplecore.service.PriceService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Price related services.
 */
@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

  /**
   * Price entity repository.
   */
  private PriceRepository priceRepository;

  /**
   * Price Service Constructor.
   * @param priceRepository Price entity repository.
   */
  public PriceServiceImpl(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public List<PriceResponse> findAll() throws PriceNotFoundException {
    List<Price> prices = priceRepository.findAllWithRelations();
    if (prices.isEmpty()) {
      // No content to response.
      throw new PriceNotFoundException(
          ExceptionCodeEnum.EMPTY_PRICES.name(),
          "Price not found for productId",
          HttpStatus.NOT_FOUND
      );
    }
    // Response mapped list.
    return PriceMapper.map(prices);
  }

  @Override
  public PriceResponse findPrice(LocalDateTime date, Integer productId, Integer brandId) throws PriceNotFoundException {
    // All search parameters exist, search.
    Optional<Price> optionalPrice = priceRepository.findByDateAndBrandIdAndProductId(date, brandId, productId);
    if (optionalPrice.isPresent()) {
      Price price = optionalPrice.get();
      PriceResponse response = PriceMapper.map(price);
      log.info("Price found => {}", response);
      return response;
    }
    throw new PriceNotFoundException(
        ExceptionCodeEnum.PRICE_NOT_FOUND.name(),
        String.format("Price not found for productId = %d", productId),
        HttpStatus.NOT_FOUND
    );
  }
}
