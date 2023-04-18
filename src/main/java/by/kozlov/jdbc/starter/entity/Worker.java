package by.kozlov.jdbc.starter.entity;

public class Worker {

    private Integer id;
    private String nameOfWorker;
    private String surnameOfWorker;
    private String speciality;
    private Integer rank;
    private Integer experience;
    private Brigade brigade;
    private String email;

    public Worker() {
    }

    public Worker(Integer id, String nameOfWorker, String surnameOfWorker,
                  String speciality, Integer rank, Integer experience, Brigade brigade, String email) {
        this.id = id;
        this.nameOfWorker = nameOfWorker;
        this.surnameOfWorker = surnameOfWorker;
        this.speciality = speciality;
        this.rank = rank;
        this.experience = experience;
        this.brigade = brigade;
        this.email = email;
    }

    public Worker(String nameOfWorker, String surnameOfWorker,
                  String speciality, Integer rank, Integer experience, Brigade brigade) {
        this.nameOfWorker = nameOfWorker;
        this.surnameOfWorker = surnameOfWorker;
        this.speciality = speciality;
        this.rank = rank;
        this.experience = experience;
        this.brigade = brigade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfWorker() {
        return nameOfWorker;
    }

    public void setNameOfWorker(String nameOfWorker) {
        this.nameOfWorker = nameOfWorker;
    }

    public String getSurnameOfWorker() {
        return surnameOfWorker;
    }

    public void setSurnameOfWorker(String surnameOfWorker) {
        this.surnameOfWorker = surnameOfWorker;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", nameOfWorker='" + nameOfWorker + '\'' +
                ", surnameOfWorker='" + surnameOfWorker + '\'' +
                ", speciality='" + speciality + '\'' +
                ", rank=" + rank +
                ", experience=" + experience +
                ", brigade=" + brigade +
                ", email='" + email + '\'' +
                '}';
    }
}
