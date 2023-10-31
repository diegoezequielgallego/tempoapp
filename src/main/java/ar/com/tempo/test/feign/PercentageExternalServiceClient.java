package ar.com.tempo.test.feign;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "percentage-external-service", url = "www.external-service.com")
public interface PercentageExternalServiceClient {

    @GetMapping("/percentage")
    PercentageResponseDTO getPercentage();
}
