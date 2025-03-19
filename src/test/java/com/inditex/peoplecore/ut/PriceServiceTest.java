package com.inditex.peoplecore.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.inditex.peoplecore.dto.PriceResponse;
import com.inditex.peoplecore.repository.PriceRepository;
import com.inditex.peoplecore.service.PriceService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceService priceService;

  @Test
  void testFindAllWithNoPrices() {
    // Dado: El repositorio devuelve una lista vacía
    when(priceRepository.findAllWithRelations()).thenReturn(Collections.emptyList());

    // Cuando: Llamo al método findAll
    ResponseEntity<List<PriceResponse>> response = priceService.findAll();

    // Entonces: Verifico que retorna NO_CONTENT
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertNull(response.getBody());
  }

}
