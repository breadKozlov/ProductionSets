package by.kozlov.spring.service;

import by.kozlov.spring.dto.MaterialDto;
import by.kozlov.spring.mapper.MaterialMapper;
import by.kozlov.spring.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    public List<MaterialDto> findAll() {

        return materialRepository.findAll().stream()
                .map(materialMapper::mapFrom).collect(Collectors.toList());
    }

    public Optional<MaterialDto> findById(Integer id) {

        return materialRepository.findById(id)
                .map(materialMapper::mapFrom);
    }

}

