package ar.com.tempo.test.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@SuperBuilder
public class PercentageResponseDTO {
    private double result;
    private HttpStatus status;
}
