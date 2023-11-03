package ar.com.tempo.test;

import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.feign.PercentageExternalServiceClient;
import ar.com.tempo.test.service.PercentageExternalServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.naming.ServiceUnavailableException;

@SpringBootTest
@ActiveProfiles("prod")
public class PercentageExternalServiceImplTests {

    @Mock
    private PercentageExternalServiceClient externalServiceClient;

    @InjectMocks
    private PercentageExternalServiceImpl percentageExternalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetPercentageSuccessful() throws ServiceUnavailableException {
        PercentageResponseDTO mockResponse = new PercentageResponseDTO();
        mockResponse.setResult(5.0);
        Mockito.when(externalServiceClient.getPercentage()).thenReturn(mockResponse);
        PercentageResponseDTO result = percentageExternalService.getPercentage();
        Assertions.assertEquals(5.0, result.getResult());
    }

    @Test
    void testGetPercentageServiceUnavailable() {
        Mockito.when(externalServiceClient.getPercentage()).thenThrow(new RuntimeException("Service unavailable"));
        Assertions.assertThrows(ServiceUnavailableException.class, () -> percentageExternalService.getPercentage());
    }

    @Test
    void testGetPercentageFirstCallSuccessfulSecondCallFails() throws ServiceUnavailableException {
        PercentageResponseDTO firstCallResponse = new PercentageResponseDTO();
        firstCallResponse.setResult(5.0);
        Mockito.when(externalServiceClient.getPercentage()).thenReturn(firstCallResponse);
        PercentageResponseDTO result1 = percentageExternalService.getPercentage();
        Assertions.assertEquals(5.0, result1.getResult());

        Mockito.when(externalServiceClient.getPercentage()).thenThrow(new RuntimeException("Service unavailable"));
        PercentageResponseDTO result2 = percentageExternalService.getPercentage();
        Assertions.assertEquals(5.0, result2.getResult());
    }

}
