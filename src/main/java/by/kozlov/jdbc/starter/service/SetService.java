package by.kozlov.jdbc.starter.service;

import by.kozlov.jdbc.starter.dao.SetDao;
import by.kozlov.jdbc.starter.dto.SetDto;
import by.kozlov.jdbc.starter.mapper.SetMapper;
import java.util.List;
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

    public static SetService getInstance() {
        return INSTANCE;
    }
}
