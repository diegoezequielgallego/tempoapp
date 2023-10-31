package ar.com.tempo.test.utils;

import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PercentageResponseDTOConverter implements AttributeConverter<PercentageResponseDTO, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(PercentageResponseDTO input) {
        try {
            return objectMapper.writeValueAsString(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir InputRequestDTO a JSON", e);
        }
    }

    @Override
    public PercentageResponseDTO convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, PercentageResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a InputRequestDTO", e);
        }
    }
}
