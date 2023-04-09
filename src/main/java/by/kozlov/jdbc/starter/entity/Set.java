package by.kozlov.jdbc.starter.entity;

public class Set {

    private Integer id;
    private String nameOfSet;
    private Integer numberOfPartsIncluded;
    private Double rateOfSet;

    public Set() {
    }

    public Set(Integer id, String nameOfSet, Integer numberOfPartsIncluded, Double rateOfSet) {
        this.id = id;
        this.nameOfSet = nameOfSet;
        this.numberOfPartsIncluded = numberOfPartsIncluded;
        this.rateOfSet = rateOfSet;
    }

    public Set(String nameOfSet, Integer numberOfPartsIncluded, Double rateOfSet) {
        this.nameOfSet = nameOfSet;
        this.numberOfPartsIncluded = numberOfPartsIncluded;
        this.rateOfSet = rateOfSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfSet() {
        return nameOfSet;
    }

    public void setNameOfSet(String nameOfSet) {
        this.nameOfSet = nameOfSet;
    }

    public Integer getNumberOfPartsIncluded() {
        return numberOfPartsIncluded;
    }

    public void setNumberOfPartsIncluded(Integer numberOfPartsIncluded) {
        this.numberOfPartsIncluded = numberOfPartsIncluded;
    }

    public Double getRateOfSet() {
        return rateOfSet;
    }

    public void setRateOfSet(Double rateOfSet) {
        this.rateOfSet = rateOfSet;
    }

    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", nameOfSet='" + nameOfSet + '\'' +
                ", numberOfPartsIncluded='" + numberOfPartsIncluded + '\'' +
                ", rateOfSet=" + rateOfSet +
                '}';
    }
}
