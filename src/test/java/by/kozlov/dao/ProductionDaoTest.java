package by.kozlov.dao;

import by.kozlov.hibernate.starter.dao.ProductionDao;
import by.kozlov.hibernate.starter.dao.RequirementDao;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.service.DifferenceService;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.utils.TestDataImporter;
import lombok.Cleanup;
import org.checkerframework.checker.units.qual.C;
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
class ProductionDaoTest {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private final ProductionDao production = ProductionDao.getInstance();

   // @BeforeAll
    //public void initDb() {
        //TestDataImporter.importData(sessionFactory);
    //}

    //@AfterAll
   // public void finish() {
       // sessionFactory.close();
    //}

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Production> results = production.findAll(session);
        assertThat(results).hasSize(7);

        List<String> fullNames = results.stream().map(Production::fullName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Pavel Ivanov 5440 112",
                "Ivan Petrov 5440 11", "Petr Grib 6501 44", "Sergey Rak 6501 144",
                "Vitold Himmler 4371 52", "Mike Miller 4371 22", "Pavel Ivanov 54327 88");

        session.getTransaction().commit();
    }

    @Test
    void findDifference() {
        @Cleanup var session = sessionFactory.openSession();
        var requirementDao = RequirementDao.getInstance();
        session.beginTransaction();



        var list = requirementDao.findSumAllReqMat(session);
        for(Object[] objects: list) {
            for (Object object : objects) {
                System.out.print(object + " ");
            }
            System.out.println();
        }
        session.getTransaction().commit();
    }

    @Test
    void findDiff() {
        var service = DifferenceService.getInstance();
        System.out.println(service.findAllDifferenceProductionMaterials());
    }

    /*@Test
    void findAllByFirstName() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> results = userDao.findAllByFirstName(session, "Bill");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).fullName()).isEqualTo("Bill Gates");

        session.getTransaction().commit();
    }

    @Test
    void findLimitedUsersOrderedByBirthday() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        int limit = 3;
        List<User> results = userDao.findLimitedUsersOrderedByBirthday(session, limit);
        assertThat(results).hasSize(limit);

        List<String> fullNames = results.stream().map(User::fullName).collect(toList());
        assertThat(fullNames).contains("Diane Greene", "Steve Jobs", "Bill Gates");

        session.getTransaction().commit();
    }

    @Test
    void findAllByCompanyName() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> results = userDao.findAllByCompanyName(session, "Google");
        assertThat(results).hasSize(2);

        List<String> fullNames = results.stream().map(User::fullName).collect(toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Sergey Brin", "Diane Greene");

        session.getTransaction().commit();
    }

    @Test
    void findAllPaymentsByCompanyName() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Payment> applePayments = userDao.findAllPaymentsByCompanyName(session, "Apple");
        assertThat(applePayments).hasSize(5);

        List<Integer> amounts = applePayments.stream().map(Payment::getAmount).collect(toList());
        assertThat(amounts).contains(250, 500, 600, 300, 400);

        session.getTransaction().commit();
    }

    @Test
    void findAveragePaymentAmountByFirstAndLastNames() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Double averagePaymentAmount = userDao.findAveragePaymentAmountByFirstAndLastNames(session, PaymentFilter.builder()
                .firstname("Bill").build());
        assertThat(averagePaymentAmount).isEqualTo(300.0);

        session.getTransaction().commit();
    }

    @Test
    void findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object[]> results = userDao.findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(session);
        assertThat(results).hasSize(3);

        List<String> orgNames = results.stream().map(a -> (String) a[0]).collect(toList());
        assertThat(orgNames).contains("Apple", "Google", "Microsoft");

        List<Double> orgAvgPayments = results.stream().map(a -> (Double) a[1]).collect(toList());
        assertThat(orgAvgPayments).contains(410.0, 400.0, 300.0);

        session.getTransaction().commit();
    }

    @Test
    void isItPossible() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Tuple> results = userDao.isItPossible(session);
        assertThat(results).hasSize(2);

        List<String> names = results.stream().map(r -> r.get(0, User.class).fullName()).collect(toList());
        assertThat(names).contains("Sergey Brin", "Steve Jobs");

        List<Double> averagePayments = results.stream().map(r -> r.get(1, Double.class)).collect(toList());
        assertThat(averagePayments).contains(500.0, 450.0);

        session.getTransaction().commit();
    }*/
}
