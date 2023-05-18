package by.kozlov.hibernate.starter.utils;

import by.kozlov.hibernate.starter.dao.WorkersSetsDao;
import by.kozlov.hibernate.starter.dto.WorkersSetsDto;
import by.kozlov.hibernate.starter.entity.WorkersSets;
import lombok.Cleanup;
import org.hibernate.SessionFactory;

public class HibernateRunner {

    private static final SessionFactory sessionFactory = HibernateUtil.getConfig().buildSessionFactory();
    private static final WorkersSetsDao workersSets = WorkersSetsDao.getInstance();

    public static void main(String[] args) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var list = workersSets.findAllProdSetsById(session,2);
        for(var objects: list) {
            for(var object: objects) {
                System.out.print(object + " ");
            }
            System.out.println();
        }
    }
}
