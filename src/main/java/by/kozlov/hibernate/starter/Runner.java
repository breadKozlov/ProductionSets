package by.kozlov.hibernate.starter;

import by.kozlov.hibernate.starter.service.BrigadeService;
import by.kozlov.hibernate.starter.service.WorkerService;

public class Runner {
    public static void main(String[] args) {
        var brigadeService = BrigadeService.getInstance();
        System.out.println(brigadeService.findAll());
    }
}
