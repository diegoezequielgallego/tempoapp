package ar.com.tempo.test.controller;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.service.CalculationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculation")
@Api(tags = "calculation-controller")
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(final CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    public CalculationResponseDTO calculate(@RequestBody InputRequestDTO input) {
        return calculationService.calculate(input);
    }


}
