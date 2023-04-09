package by.kozlov.jdbc.starter.entity;

public class Brigade {

    private Integer id;
    private String nameOfBrigade;
    private String phoneNumberOfForeman;


    public Brigade() {
    }

    public Brigade(Integer id, String nameOfBrigade, String phoneNumberOfForeman) {
        this.id = id;
        this.nameOfBrigade = nameOfBrigade;
        this.phoneNumberOfForeman = phoneNumberOfForeman;
    }

    public Brigade(String nameOfBrigade, String phoneNumberOfForeman) {
        this.nameOfBrigade = nameOfBrigade;
        this.phoneNumberOfForeman = phoneNumberOfForeman;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfBrigade() {
        return nameOfBrigade;
    }

    public void setNameOfBrigade(String nameOfBrigade) {
        this.nameOfBrigade = nameOfBrigade;
    }

    public String getPhoneNumberOfForeman() {
        return phoneNumberOfForeman;
    }

    public void setPhoneNumberOfForeman(String phoneNumberOfForeman) {
        this.phoneNumberOfForeman = phoneNumberOfForeman;
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "id=" + id +
                ", nameOfBrigade='" + nameOfBrigade + '\'' +
                ", scheduleWork='" + phoneNumberOfForeman + '\'' +
                '}';
    }
}
