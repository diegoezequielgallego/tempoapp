package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Profile("dev")
public class PercentageExternalServiceMockImpl implements PercentageExternalService{

    protected final Log logger = LogFactory.getLog(this.getClass());
    @Override
    @Cacheable("percentageCache")
    public PercentageResponseDTO getPercentage() {
        Random random = new Random();
        double randomDouble = 1.0 + (10.0 - 1.0) * random.nextDouble();
        randomDouble = Math.round(randomDouble * 100.0) / 100.0;
        logger.info("return random value in service mock: " + randomDouble);
        return PercentageResponseDTO.builder().result(randomDouble).build();
    }
}
