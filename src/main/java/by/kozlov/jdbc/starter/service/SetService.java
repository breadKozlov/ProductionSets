package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.SetDao;
import by.kozlov.jdbc.starter.dto.SetDto;
import by.kozlov.jdbc.starter.entity.Gender;
import by.kozlov.jdbc.starter.mapper.SetMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SetService {

    private static final SetService INSTANCE = new SetService();
    private final SetMapper setMapper = SetMapper.getInstance();
    private final SetDao setDao = SetDao.getInstance();

    public List<SetDto> findAll() {
        return setDao.findAll().stream().map(
                setMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public Optional<SetDto> findById(Integer id) {
        return setDao.findById(id).map(setMapper::mapFrom);
    }

    public Optional<SetDto> find(String id) {
        return setDao.findById(Integer.parseInt(id)).map(
                setMapper::mapFrom
        );
    }

    public static SetService getInstance() {
        return INSTANCE;
    }
}
