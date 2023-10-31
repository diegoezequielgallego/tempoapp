package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private PercentageExternalService percentageExternalService;

    public CalculationResponseDTO calculate(InputRequestDTO input) {

        int maxRetries = 3;
        double result = 0;

        for (int retry = 1; retry <= maxRetries; retry++) {
            try {
                PercentageResponseDTO percentageResponseDTO = percentageExternalService.getPercentage();
                double percentage = percentageResponseDTO.getResult();
                double sum = input.getNumber1() + input.getNumber2();
                result = sum + (sum * percentage / 100);
                break;
            } catch (Exception e) {
                if (retry < maxRetries) {
                    try {
                        Thread.sleep(1000); // wait 1 second
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } else {

                    logger.error("error in access to a get percentage service.");
                }
            }
        }

        return CalculationResponseDTO.builder().result(result).build();

    }

}
