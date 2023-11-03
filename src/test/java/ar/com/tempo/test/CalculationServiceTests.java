package ar.com.tempo.test;

import ar.com.tempo.test.dto.CalculationResponseDTO;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.service.CalculationService;
import ar.com.tempo.test.service.HistoryService;
import ar.com.tempo.test.service.PercentageExternalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.ServiceUnavailableException;
import java.lang.reflect.Field;

@SpringBootTest
class CalculationServiceTests {

    @Mock
    private PercentageExternalService percentageExternalService;

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private CalculationService calculationService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        int maxRetries = 3; // Set to the value you want
        setPrivateField(calculationService, "maxRetries", maxRetries);
    }


    @Test
    void testCalculateSuccessful() throws ServiceUnavailableException {
        InputRequestDTO input = new InputRequestDTO();
        input.setNumber1(50.0);
        input.setNumber2(50.0);

        PercentageResponseDTO mockPercentageResponse = new PercentageResponseDTO();
        mockPercentageResponse.setResult(5.0);
        Mockito.when(percentageExternalService.getPercentage()).thenReturn(mockPercentageResponse);

        CalculationResponseDTO result = calculationService.calculate(input);
        Assertions.assertEquals(105.0, result.getResult());
    }


    @Test
    void testCalculateRetriesTwice() throws ServiceUnavailableException {
        InputRequestDTO input = new InputRequestDTO();
        input.setNumber1(50.0);
        input.setNumber2(50.0);

        PercentageResponseDTO mockPercentageResponse = new PercentageResponseDTO();
        mockPercentageResponse.setResult(5.0);
        Mockito.when(percentageExternalService.getPercentage())
                .thenThrow(new ServiceUnavailableException("First retry"))
                .thenThrow(new ServiceUnavailableException("Second retry"))
                .thenReturn(mockPercentageResponse);

        CalculationResponseDTO result = calculationService.calculate(input);
        Assertions.assertEquals(105.0, result.getResult());
    }

    @Test
    void testCalculateMaxRetriesExceeded() throws ServiceUnavailableException {
        InputRequestDTO input = new InputRequestDTO();
        input.setNumber1(50.0);
        input.setNumber2(50.0);

        Mockito.when(percentageExternalService.getPercentage())
                .thenThrow(new ServiceUnavailableException("First retry"))
                .thenThrow(new ServiceUnavailableException("Second retry"))
                .thenThrow(new ServiceUnavailableException("Third retry"));

        Assertions.assertThrows(IllegalStateException.class, () -> calculationService.calculate(input));
    }


    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error setting private field", e);
        }
    }

}
