package by.kozlov.dao;

import by.kozlov.hibernate.starter.dao.ProductionDao;
import by.kozlov.hibernate.starter.dao.RequirementDao;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.entity.Requirement;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.utils.TestDataImporter;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class RequirementDaoTest {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private final RequirementDao requirementDao = RequirementDao.getInstance();

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Requirement> results = requirementDao.findAll(session);
        assertThat(results).hasSize(6);

        List<String> fullNames = results.stream().map(Requirement::fullName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("5440 K30 7.8 123",
                "5440 K40 6.8 123","6501 K40 5.8 400","4371 K50 3.8 10","54327 K50 4.8 245","6501 K40 2.8 328");

        session.getTransaction().commit();
    }

    @Test
    void findDifferenceBetweenReqAndRelMaterialsProduction() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object[]> results = requirementDao.findSumAllReqMat(session);
        assertThat(results).hasSize(3);

        List<String> orgNames = results.stream().map(a -> (String) a[0]).collect(toList());
        assertThat(orgNames).contains("K30", "K40", "K50");

        List<Double> orgSumReqProd = results.stream().map(a -> (Double) a[1]).collect(toList());
        assertThat(orgSumReqProd).contains(959.4, 4074.8, 1214.0);

        List<Double> orgSumRelProd = results.stream().map(a -> (Double) a[2]).collect(toList());
        assertThat(orgSumRelProd).contains(null,747.5,940.9);

        session.getTransaction().commit();
    }

    @Test
    void findAllBySetId() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<Requirement> results = requirementDao.findAllBySetId(session,1);

        assertThat(results).hasSize(2);
        List<String> fullNames = results.stream().map(Requirement::fullName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("5440 K30 7.8 123",
                "5440 K40 6.8 123");
        System.out.println(results);
        session.getTransaction().commit();
    }
}
