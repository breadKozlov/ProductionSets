package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.BrigadeDao;
import by.kozlov.hibernate.starter.dto.BrigadeDto;
import by.kozlov.hibernate.starter.mapper.BrigadeMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrigadeService {

    private static final BrigadeService INSTANCE = new BrigadeService();
    private final BrigadeDao brigadeDao = BrigadeDao.getInstance();
    private final BrigadeMapper brigadeMapper = BrigadeMapper.getInstance();

    public Optional<BrigadeDto> find(String id) {
        return brigadeDao.findById(Integer.parseInt(id)).map(
                brigadeMapper::mapFrom
        );
    }
    public List<BrigadeDto> findAll() {
        return brigadeDao.findAll().stream().map(
                brigadeMapper::mapFrom
        ).collect(Collectors.toList());
    }

    public Optional<BrigadeDto> findById(Integer id) {
        return brigadeDao.findById(id).map(brigadeMapper::mapFrom);
    }

    public static BrigadeService getInstance() {
        return INSTANCE;
    }

}
