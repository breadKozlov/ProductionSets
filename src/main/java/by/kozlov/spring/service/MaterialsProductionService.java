package by.kozlov.spring.service;

import by.kozlov.spring.database.repository.MaterialsProductionRepository;
import by.kozlov.spring.dto.MaterialsProductionCreateEditDto;
import by.kozlov.spring.dto.MaterialsProductionReadDto;
import by.kozlov.spring.mapper.MaterialsProductionCreateEditMapper;
import by.kozlov.spring.mapper.MaterialsProductionReadMapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MaterialsProductionService {

    private final MaterialsProductionRepository materialProductionRepository;
    private final MaterialsProductionReadMapper materialsProductionReadMapper;
    private final MaterialsProductionCreateEditMapper materialsProductionCreateEditMapper;

    public List<MaterialsProductionReadDto> findAll(Pageable page) {
        return materialProductionRepository.findAll(page).stream()
                .map(materialsProductionReadMapper::map).collect(Collectors.toList());
    }

    public Optional<MaterialsProductionReadDto> findById(Integer id) {
        return materialProductionRepository.findById(id)
                .map(materialsProductionReadMapper::map);
    }

    public List<MaterialsProductionReadDto> findAllByBrigadeId(Integer id, Pageable page) {
        return materialProductionRepository.findAllByBrigadeId(id,page).stream()
                .map(materialsProductionReadMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public MaterialsProductionReadDto create(MaterialsProductionCreateEditDto materialsProductionDto) {
        return Optional.of(materialsProductionDto)
                .map(materialsProductionCreateEditMapper::map)
                .map(materialProductionRepository::save)
                .map(materialsProductionReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public boolean delete(Integer id) {
        return materialProductionRepository.findById(id)
                .map(entity -> {
                    materialProductionRepository.delete(entity);
                    materialProductionRepository.flush();
                    return true;
                }).orElse(false);
    }

    @Transactional
    public Optional<MaterialsProductionReadDto> update(Integer id, MaterialsProductionCreateEditDto productionDto) {
        return materialProductionRepository.findById(id)
                .map(entity -> materialsProductionCreateEditMapper.map(productionDto,entity))
                .map(materialProductionRepository::saveAndFlush)
                .map(materialsProductionReadMapper::map);

    }
}
