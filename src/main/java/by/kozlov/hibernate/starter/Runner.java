package by.kozlov.hibernate.starter;

import by.kozlov.hibernate.starter.service.BrigadeService;
import by.kozlov.hibernate.starter.service.WorkerService;

public class Runner {
    public static void main(String[] args) {
        var brigadeService = BrigadeService.getInstance();
        var brigade1 = brigadeService.findById(1);
        var brigade2 = brigadeService.findById(1);
        System.out.println(brigade2);


    }
}
