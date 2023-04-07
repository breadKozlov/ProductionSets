package by.kozlov.jdbc.starter.entity;

import java.util.Date;

public class Production {

    private Date dateOfProduction;
    private int idSet;
    private int idWorker;
    private int madeSets;

    public Production(int idWorker, int idSet, int madeSets, Date dateOfProduction) {
        this.idWorker = idWorker;
        this.idSet = idSet;
        this.madeSets = madeSets;
        this.dateOfProduction = dateOfProduction;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(Date dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public int getIdSet() {
        return idSet;
    }

    public void setIdSet(int idSet) {
        this.idSet = idSet;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public int getMadeSets() {
        return madeSets;
    }

    public void setMadeSets(int madeSets) {
        this.madeSets = madeSets;
    }

    @Override
    public String toString() {
        return "Production{" +
                "dateOfProduction=" + dateOfProduction +
                ", idSet=" + idSet +
                ", idWorker=" + idWorker +
                ", madeSets=" + madeSets +
                '}';
    }

    public Production() {}
}
