package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dto.SetDto;
import by.kozlov.hibernate.starter.dto.WorkersSetsDto;
import by.kozlov.hibernate.starter.mapper.SetMapper;

import java.util.ArrayList;
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

    public List<SetDto> findAllFreeSets(List<WorkersSetsDto> workersSetsDtoList) {

        List<SetDto> allList = this.findAll();
        List<SetDto> resultList = new ArrayList<>();

        if (workersSetsDtoList.isEmpty()) {
            resultList = allList;
        } else {
            for (SetDto set: allList) {

            boolean flag = false;

            for(WorkersSetsDto workersSet: workersSetsDtoList) {
                if (set.getNameOfSet().equals(workersSet.getSet().getNameOfSet())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                resultList.add(set);
            }
            }
        }
        return resultList;
    }

    public static SetService getInstance() {
        return INSTANCE;
    }
}
