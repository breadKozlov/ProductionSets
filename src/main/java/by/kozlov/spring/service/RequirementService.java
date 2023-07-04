package by.kozlov.spring.service;

import by.kozlov.spring.dto.*;
import by.kozlov.spring.database.entity.Requirement;
import by.kozlov.spring.mapper.*;
import by.kozlov.spring.database.repository.RequirementRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final RequirementReadMapper requirementReadMapper;
    private final RequirementCreateEditMapper requirementCreateEditMapper;

    public List<RequirementReadDto> findAllBySetId(Integer id) {
        return requirementRepository.findAllBySetId(id).stream()
                .map(requirementReadMapper::map).toList();
    }

    public List<RequirementReadDto> findAll(Sort sort) {
        return requirementRepository.findAll(sort).stream()
                .map(requirementReadMapper::map).toList();
    }

    public List<Object[]> findSumReqMaterials() {
        List<Object[]> sum;
        sum = requirementRepository.findSumAllReqMat();
        return sum;
    }

    public Optional<RequirementReadDto> findById(Integer id) {
        return requirementRepository.findById(id)
                .map(requirementReadMapper::map);
    }

    @Transactional
    public RequirementReadDto create(RequirementCreateEditDto requirementDto) {
        return Optional.of(requirementDto)
                .map(requirementCreateEditMapper::map)
                .map(requirementRepository::save)
                .map(requirementReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<RequirementReadDto> update(Integer id, RequirementCreateEditDto requirementDto) {
        return requirementRepository.findById(id)
                .map(entity -> requirementCreateEditMapper.map(requirementDto,entity))
                .map(requirementRepository::saveAndFlush)
                .map(requirementReadMapper::map);
    }
}
