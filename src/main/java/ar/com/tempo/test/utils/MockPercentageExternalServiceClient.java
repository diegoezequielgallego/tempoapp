package ar.com.tempo.test.utils;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.feign.PercentageExternalServiceClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MockPercentageExternalServiceClient implements PercentageExternalServiceClient {

    @Override
    public PercentageResponseDTO getPercentage() {
        return PercentageResponseDTO.builder().result(2.0).build();
    }
}
