package by.kozlov.jdbc.starter.entity;

import java.time.LocalDateTime;

public class Production {

    private Integer id;
    private Worker worker;
    private Set set;
    private Integer madeSets;
    private LocalDateTime dateOfProduction;

    public Production() {
    }

    public Production(Integer id, Worker worker, Set set, Integer madeSets, LocalDateTime dateOfProduction) {
        this.id = id;
        this.worker = worker;
        this.set = set;
        this.madeSets = madeSets;
        this.dateOfProduction = dateOfProduction;
    }

    public Production(Worker worker, Set set, Integer madeSets, LocalDateTime dateOfProduction) {
        this.worker = worker;
        this.set = set;
        this.madeSets = madeSets;
        this.dateOfProduction = dateOfProduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Integer getMadeSets() {
        return madeSets;
    }

    public void setMadeSets(Integer madeSets) {
        this.madeSets = madeSets;
    }

    public LocalDateTime getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(LocalDateTime dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    @Override
    public String toString() {
        return "Production{" +
                "id=" + id +
                ", worker=" + worker +
                ", set=" + set +
                ", madeSets=" + madeSets +
                ", dateOfProduction=" + dateOfProduction +
                '}';
    }
}
