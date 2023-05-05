package by.kozlov;

import by.kozlov.hibernate.starter.entity.Worker;
import by.kozlov.hibernate.starter.service.WorkerService;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

public class AppTest
{
    @Test
    public void findAllWorkers() {

        @Cleanup var sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        var workers = session.createQuery("FROM Worker", Worker.class)
                .list();

        System.out.println(workers);

        session.getTransaction().commit();

    }

    @Test
    public void findAllWorkersService() {
        var workersService = WorkerService.getInstance();
        System.out.println(workersService.findAll());
    }
}
