package by.kozlov.jdbc.starter.dao;

import by.kozlov.jdbc.starter.service.MaterialsProductionService;

public class JdbcRunner {

    public static void main(String[] args) {

        var materialsProductionDao = MaterialsProductionDao.getInstance();
        var materialsService = MaterialsProductionService.getInstance();
        System.out.println(materialsProductionDao.findAll());
        System.out.println(materialsService.findAll());

    }
}
