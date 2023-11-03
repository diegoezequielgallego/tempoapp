package ar.com.tempo.test.controller;

import ar.com.tempo.test.domain.History;
import ar.com.tempo.test.dto.HistoryDTO;
import ar.com.tempo.test.service.HistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public Page<HistoryDTO> getCallHistory(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("timestamp")));
        Page<History> historyPage = historyService.findAll(pageable);

        Page<HistoryDTO> historyDTOPage = historyPage.map(history -> {
            HistoryDTO historyDTO = objectMapper.convertValue(history, HistoryDTO.class);
            return historyDTO;
        });

        return historyDTOPage;
    }

}
