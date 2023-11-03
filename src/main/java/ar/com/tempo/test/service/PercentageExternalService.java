package ar.com.tempo.test.service;

import ar.com.tempo.test.dto.PercentageResponseDTO;

import javax.naming.ServiceUnavailableException;

public interface PercentageExternalService {

    PercentageResponseDTO getPercentage() throws ServiceUnavailableException;
}
