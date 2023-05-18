package by.kozlov.dao;

import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dao.WorkerRepository;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.utils.TestDataImporter;
import lombok.Cleanup;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class WorkerDaoTest {

    private final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findByEmail() {
        @Cleanup var session = sessionFactory.openSession();
        var workerRepository = new WorkerRepository(session);
        session.beginTransaction();
        var workerWithEmail = workerRepository.findByEmail("braga@tut.by");
        assertThat(workerWithEmail.orElseThrow().getNameOfWorker() + " "
                + workerWithEmail.orElseThrow().getSurnameOfWorker()).isEqualTo("Pavel Ivanov");
        session.getTransaction().commit();
    }
}
