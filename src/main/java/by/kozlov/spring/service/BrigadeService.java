package by.kozlov.spring.service;

import by.kozlov.spring.repository.BrigadeRepository;
import by.kozlov.spring.dto.BrigadeDto;
import by.kozlov.spring.mapper.BrigadeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrigadeService {

    private final BrigadeRepository brigadeRepository;
    private final BrigadeMapper brigadeMapper;
    @Autowired
    public BrigadeService(BrigadeRepository brigadeRepository, BrigadeMapper brigadeMapper) {
        this.brigadeRepository = brigadeRepository;
        this.brigadeMapper = brigadeMapper;
    }

    public List<BrigadeDto> findAll() {
        return brigadeRepository.findAll().stream()
                    .map(brigadeMapper::mapFrom).collect(Collectors.toList());
    }

    public Optional<BrigadeDto> findById(Integer id) {
        return brigadeRepository.findById(id)
                    .map(brigadeMapper::mapFrom);
    }
}
