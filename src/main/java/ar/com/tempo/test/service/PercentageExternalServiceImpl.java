package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.feign.PercentageExternalServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import javax.naming.ServiceUnavailableException;

@Service
@Profile("prod")
@Scope("singleton")
public class PercentageExternalServiceImpl implements PercentageExternalService{

    @Autowired
    private PercentageExternalServiceClient externalServiceClient;

    private volatile PercentageResponseDTO lastValue;

    private final Object lock = new Object();

    @Override
    @Cacheable("percentageCache")
    public PercentageResponseDTO getPercentage() throws ServiceUnavailableException {
        try {
            PercentageResponseDTO newValue = externalServiceClient.getPercentage();
            synchronized (lock) {
                lastValue = newValue;
            }

            return newValue;
        } catch (Exception e) {
            synchronized (lock) {
                if (lastValue != null) {
                    return lastValue;
                }
            }
            throw new ServiceUnavailableException("error to call external service.");
        }
    }
}
