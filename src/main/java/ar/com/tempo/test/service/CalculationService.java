package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    @Autowired
    private PercentageExternalService percentageExternalService;

    public CalculationResponseDTO calculate(InputRequestDTO input) {

        PercentageResponseDTO percentageResponseDTO = percentageExternalService.getPercentage();
        double percentage = percentageResponseDTO.getResult();

        double sum = input.getNumber1() + input.getNumber2();
        double result = sum + (sum * percentage / 100);
        return CalculationResponseDTO.builder().result(result).build();

    }

}
