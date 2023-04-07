package by.kozlov.jdbc.starter.utils;

import by.kozlov.jdbc.starter.dao.ProductionDao;

public class JdbcRunner {

    public static void main(String[] args) {

        var productionDao = ProductionDao.getInstance();

        System.out.println(productionDao.findAll());

    }
}
