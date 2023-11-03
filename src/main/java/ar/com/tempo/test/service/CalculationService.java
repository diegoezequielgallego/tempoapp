package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;

@Service
public class CalculationService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private PercentageExternalService percentageExternalService;

    @Autowired
    private HistoryService historyService;

    @Value("${calculation.max.retries:3}")
    private int maxRetries;

    public CalculationResponseDTO calculate(InputRequestDTO input) {
        double result = 0;

        for (int retry = 1; retry <= maxRetries; retry++) {
            try {
                result = calculateWithRetry(input);
                break;
            } catch (Exception e) {
                logger.error("Error in access to the get percentage service (Retry " + retry + ")");
                if (retry < maxRetries) {
                    waitForRetry();
                } else {
                    throw new IllegalStateException("Error in access to the get percentage service after " + maxRetries + " retries.");
                }
            }
        }

        return CalculationResponseDTO.builder().result(result).build();
    }

    private double calculateWithRetry(InputRequestDTO input) throws ServiceUnavailableException {
        PercentageResponseDTO percentageResponseDTO = percentageExternalService.getPercentage();
        double percentage = percentageResponseDTO.getResult();
        double sum = input.getNumber1() + input.getNumber2();
        double result = sum + (sum * percentage / 100);
        historyService.saveCallHistoryAsync(input, percentageResponseDTO, result);
        return result;
    }

    private void waitForRetry() {
        try {
            Thread.sleep(1000); // wait 1 second to retry
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}