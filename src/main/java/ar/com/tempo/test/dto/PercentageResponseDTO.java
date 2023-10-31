package ar.com.tempo.test.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class PercentageResponseDTO {
    private double result;
    private HttpStatus status;
}
