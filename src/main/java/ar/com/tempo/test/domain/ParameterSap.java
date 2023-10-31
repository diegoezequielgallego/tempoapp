package ar.com.tempo.test.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Access(AccessType.FIELD)
@Table(name = "parameterSap")
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ParameterSap  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    protected LocalDateTime creation = LocalDateTime.now();
    private String material;
    private String description;

}
