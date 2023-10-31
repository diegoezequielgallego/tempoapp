package ar.com.tempo.test.domain;

import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.utils.InputRequestDTOConverter;
import ar.com.tempo.test.utils.PercentageResponseDTOConverter;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Access(AccessType.FIELD)
@Table(name = "history")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    @Convert(converter = InputRequestDTOConverter.class)
    private InputRequestDTO input;
    @Convert(converter = PercentageResponseDTOConverter.class)
    private PercentageResponseDTO response;
    private double result;

}
