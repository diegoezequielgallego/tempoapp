package ar.com.tempo.test.feign;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "percentage-external-service", url = "http://localhost:8080")
public interface PercentageExternalServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/percentage")
    PercentageResponseDTO getPercentage();
}
