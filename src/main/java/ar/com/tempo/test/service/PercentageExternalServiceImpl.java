package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.feign.PercentageExternalServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Service
@Profile("prod")
public class PercentageExternalServiceImpl implements PercentageExternalService{

    @Autowired
    private PercentageExternalServiceClient externalServiceClient;

    @Override
    @Cacheable("percentageCache")
    public PercentageResponseDTO getPercentage() {
        return externalServiceClient.getPercentage();
    }
}
