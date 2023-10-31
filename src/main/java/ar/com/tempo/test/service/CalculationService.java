package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    //@Autowired
    //private PercentageExternalServiceClient externalServiceClient;

    public CalculationResponseDTO calculate(InputRequestDTO input) {

        //double percentage = externalServiceClient.getPercentage();

        double percentage = 2.0;

        double sum = input.getNumber1() + input.getNumber2();
        double result = sum + (sum * percentage / 100);
        return CalculationResponseDTO.builder().result(result).build();

    }

}
