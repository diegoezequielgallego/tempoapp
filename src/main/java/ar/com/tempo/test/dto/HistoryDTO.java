package ar.com.tempo.test.dto;

import ar.com.tempo.test.utils.InputRequestDTOConverter;
import ar.com.tempo.test.utils.PercentageResponseDTOConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class HistoryDTO {

    private Long id;
    private Date timestamp;
    private InputRequestDTO input;
    private PercentageResponseDTO response;
    private double result;

}
