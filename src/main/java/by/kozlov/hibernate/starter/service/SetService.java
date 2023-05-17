package by.kozlov.hibernate.starter.service;

import by.kozlov.hibernate.starter.dao.*;
import by.kozlov.hibernate.starter.dto.ProductionDto;
import by.kozlov.hibernate.starter.dto.SetDto;
import by.kozlov.hibernate.starter.dto.WorkerDto;
import by.kozlov.hibernate.starter.dto.WorkersSetsDto;
import by.kozlov.hibernate.starter.mapper.*;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SetService {

    private final SessionFactory sessionFactory;

    private static final SetService INSTANCE = new SetService();
    private final SetMapper setMapper = new SetMapper();
    private final SetRepository setRepository;

    private final Session session;

    private SetService() {
        sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        setRepository = new SetRepository(session);
    }

    public List<SetDto> findAll() {
        try (session) {
            List<SetDto> sets;
            session.beginTransaction();
            sets = setRepository.findAll().stream()
                    .map(setMapper::mapFrom).collect(Collectors.toList());
            session.getTransaction().commit();
            return sets;
        }
    }

    public Optional<SetDto> findById(Integer id) {
        try (session) {
            Optional<SetDto> set;
            session.beginTransaction();
            set = setRepository.findById(id)
                    .map(setMapper::mapFrom);
            session.getTransaction().commit();
            return set;
        }
    }

    public Optional<SetDto> find(String id) {
        try (session) {
            Optional<SetDto> production;
            session.beginTransaction();
            production = setRepository.findById(Integer.parseInt(id))
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
