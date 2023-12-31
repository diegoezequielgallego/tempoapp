package ar.com.tempo.test.service;

import ar.com.tempo.test.domain.History;
import ar.com.tempo.test.dto.InputRequestDTO;
import ar.com.tempo.test.dto.PercentageResponseDTO;
import ar.com.tempo.test.repository.HistoryRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class HistoryService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private HistoryRepository historyRepository;


    @Async
    @ExceptionHandler({Exception.class})
    public void saveCallHistoryAsync(InputRequestDTO input, PercentageResponseDTO percentageResponseDTO, double result) {
        try {
            History history = History.builder()
                    .timestamp(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                    .input(input)
                    .response(percentageResponseDTO)
                    .result(result)
                    .build();
            historyRepository.save(history);
        } catch (Exception e) {
            logger.error("Error to save history.", e);
        }
    }


    public Page<History> findAll(PageRequest pageable) {
        return historyRepository.findAll(pageable);
    }
}
