package by.kozlov.spring.service;

import by.kozlov.spring.database.repository.MaterialRepository;
import by.kozlov.spring.dto.MaterialReadDto;
import by.kozlov.spring.mapper.MaterialReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialReadMapper materialReadMapper;

    public List<MaterialReadDto> findAll() {

        return materialRepository.findAll().stream()
                .map(materialReadMapper::map).toList();
    }

    public Optional<MaterialReadDto> findById(Integer id) {

        return materialRepository.findById(id)
                .map(materialReadMapper::map);
    }

}

