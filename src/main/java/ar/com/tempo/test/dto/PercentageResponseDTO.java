package ar.com.tempo.test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PercentageResponseDTO {
    private double result;
}
