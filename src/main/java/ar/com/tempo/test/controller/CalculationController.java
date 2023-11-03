package ar.com.tempo.test.controller;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.service.CalculationService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

@RestController
@RequestMapping("/calculation")
@Api(tags = "calculation-controller")
public class CalculationController {

    private final CalculationService calculationService;
    private final Bucket bucket;

    public CalculationController(final CalculationService calculationService) {
        this.calculationService = calculationService;
        Bandwidth limit = Bandwidth.classic(3, Refill.greedy(3, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponseDTO> calculate(@RequestBody InputRequestDTO input) {
        if (bucket.tryConsume(1)) {
            CalculationResponseDTO calculationResponseDTO = calculationService.calculate(input);
            return ResponseEntity.ok(calculationResponseDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many requests. Please wait.");
        }
    }


}
