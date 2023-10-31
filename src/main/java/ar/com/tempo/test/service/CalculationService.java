package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.feign.PercentageExternalServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    @Autowired
    private PercentageExternalServiceClient externalServiceClient;

    public CalculationResponseDTO calculate(InputRequestDTO input) {

        PercentageResponseDTO percentageResponseDTO = externalServiceClient.getPercentage();
        double percentage = percentageResponseDTO.getResult();

        double sum = input.getNumber1() + input.getNumber2();
        double result = sum + (sum * percentage / 100);
        return CalculationResponseDTO.builder().result(result).build();

    }

}
