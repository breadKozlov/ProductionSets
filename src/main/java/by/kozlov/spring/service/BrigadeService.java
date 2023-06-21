package by.kozlov.spring.service;

import by.kozlov.spring.database.repository.BrigadeRepository;
import by.kozlov.spring.dto.BrigadeReadDto;
import by.kozlov.spring.mapper.BrigadeReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrigadeService {

    private final BrigadeRepository brigadeRepository;
    private final BrigadeReadMapper brigadeReadMapper;

    public List<BrigadeReadDto> findAll() {
        return brigadeRepository.findAll().stream()
                    .map(brigadeReadMapper::map).collect(Collectors.toList());
    }

    public Optional<BrigadeReadDto> findById(Integer id) {
        return brigadeRepository.findById(id)
                    .map(brigadeReadMapper::map);
    }
}
