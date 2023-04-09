package by.kozlov.jdbc.starter.utils;

import by.kozlov.jdbc.starter.dao.BrigadeDao;
import by.kozlov.jdbc.starter.dao.MaterialDao;
import by.kozlov.jdbc.starter.dao.ShiftHourDao;
import by.kozlov.jdbc.starter.dao.WorkerDao;
import by.kozlov.jdbc.starter.entity.Material;

public class JdbcRunner {

    public static void main(String[] args) {

        var materialDao = MaterialDao.getInstance();
        var shiftHourDao = ShiftHourDao.getInstance();
        var brigadeDao = BrigadeDao.getInstance();
        var workerDao = WorkerDao.getInstance();

        System.out.println(materialDao.findAll());
        System.out.println(materialDao.findById(2));
        //System.out.println(materialDao.update(new Material(7,"234","update")));
        //System.out.println(materialDao.delete(7));
        System.out.println(workerDao.findAll());
        System.out.println(brigadeDao.findAll());
        System.out.println(shiftHourDao.findAll());

    }
}
