package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.MaterialDao;
import by.kozlov.jdbc.starter.dto.MaterialDto;
import by.kozlov.jdbc.starter.dto.SetDto;
import by.kozlov.jdbc.starter.mapper.MaterialMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaterialService {

    private static final MaterialService INSTANCE = new MaterialService();
    private final MaterialDao materialDao = MaterialDao.getInstance();
    private final MaterialMapper materialMapper = MaterialMapper.getInstance();

    public List<MaterialDto> findAll() {
        return materialDao.findAll().stream().map(
                materialMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public Optional<MaterialDto> findById(Integer id) {
        return materialDao.findById(id).map(materialMapper::mapFrom);
    }

    public Optional<MaterialDto> find(String id) {
        return materialDao.findById(Integer.parseInt(id)).map(
                materialMapper::mapFrom
        );
    }

    public static MaterialService getInstance() {
        return INSTANCE;
    }
}
