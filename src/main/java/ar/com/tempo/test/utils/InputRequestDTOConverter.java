package ar.com.tempo.test.utils;

import ar.com.tempo.test.dto.InputRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class InputRequestDTOConverter implements AttributeConverter<InputRequestDTO, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(InputRequestDTO input) {
        try {
            return objectMapper.writeValueAsString(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir InputRequestDTO a JSON", e);
        }
    }

    @Override
    public InputRequestDTO convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, InputRequestDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a InputRequestDTO", e);
        }
    }
}
