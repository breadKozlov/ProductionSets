package by.kozlov.jdbc.starter.entity;

public class ShiftHour {

    private Integer id;
    private String nameOfShiftHour;

    public ShiftHour() {
    }

    public ShiftHour(String nameOfShiftHour) {
        this.nameOfShiftHour = nameOfShiftHour;
    }

    public ShiftHour(Integer id, String nameOfShiftHour) {
        this.id = id;
        this.nameOfShiftHour = nameOfShiftHour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfShiftHour() {
        return nameOfShiftHour;
    }

    public void setNameOfShiftHour(String nameOfShiftHour) {
        this.nameOfShiftHour = nameOfShiftHour;
    }

    @Override
    public String toString() {
        return "ShiftHour{" +
                "id=" + id +
                ", nameOfShiftHour='" + nameOfShiftHour + '\'' +
                '}';
    }
}
