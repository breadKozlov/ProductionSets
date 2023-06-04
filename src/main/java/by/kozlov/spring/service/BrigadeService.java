package by.kozlov.spring.service;

import by.kozlov.spring.repository.BrigadeRepository;
import by.kozlov.spring.dto.BrigadeDto;
import by.kozlov.spring.mapper.BrigadeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrigadeService {

    @Autowired
    private final BrigadeRepository brigadeRepository;
    @Autowired
    private final BrigadeMapper brigadeMapper;

    public List<BrigadeDto> findAll() {
        return brigadeRepository.findAll().stream()
                    .map(brigadeMapper::mapFrom).collect(Collectors.toList());
    }

    public Optional<BrigadeDto> findById(Integer id) {
        return brigadeRepository.findById(id)
                    .map(brigadeMapper::mapFrom);
    }
}
