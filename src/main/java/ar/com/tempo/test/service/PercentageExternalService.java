package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import org.springframework.cache.annotation.Cacheable;

public interface PercentageExternalService {

    PercentageResponseDTO getPercentage();
}
