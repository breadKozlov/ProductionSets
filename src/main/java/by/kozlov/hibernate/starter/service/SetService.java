package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.SetDto;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.dto.WorkersSetsDto;
import by.kozlov.hibernate.starter.mapper.SetMapper;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SetService {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    private static final SetService INSTANCE = new SetService();
    private final SetMapper setMapper = SetMapper.getInstance();
    private final SetDao setDao = SetDao.getInstance();

    public List<SetDto> findAll() {
        try (var session = sessionFactory.openSession()) {
            List<SetDto> sets;
            session.beginTransaction();
            sets = setDao.findAll(session).stream()
                    .map(setMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return sets;
        }
    }

    public Optional<SetDto> findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            Optional<SetDto> set;
            session.beginTransaction();
            set = setDao.findById(session,id)
                    .map(setMapper::mapFrom);
            session.getTransaction().commit();
            return set;
        }
    }

    public Optional<SetDto> find(String id) {
        try (var session = sessionFactory.openSession()) {
            Optional<SetDto> production;
            session.beginTransaction();
            production = setDao.findById(session,Integer.parseInt(id))
                    .map(setMapper::mapFrom);
            session.getTransaction().commit();
            return production;
        }
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
