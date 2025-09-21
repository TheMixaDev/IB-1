package net.alephdev.lab1.controller;

import net.alephdev.lab1.dto.DataDto;
import net.alephdev.lab1.model.Data;
import net.alephdev.lab1.repository.DataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataRepository dataRepository;

    public DataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping
    public List<DataDto> getData() {
        return dataRepository.findByAdmin(false).stream()
                .map(this::sanitizeData)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DataDto createData(@RequestBody DataDto dataDto) {
        Data data = new Data(dataDto.getContent(), false);
        return sanitizeData(dataRepository.save(data));
    }

    private DataDto sanitizeData(Data data) {
        DataDto dto = new DataDto();
        dto.setContent(HtmlUtils.htmlEscape(data.getContent()));
        return dto;
    }
}
