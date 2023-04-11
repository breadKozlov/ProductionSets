package by.kozlov.jdbc.starter.entity;

public class Schedule {

    private Integer id;
    private String nameMonth;
    private ShiftHour shiftHour;
    private Brigade brigade;

    public Schedule() {
    }

    public Schedule(Integer id, String nameMonth, ShiftHour shiftHour, Brigade brigade) {
        this.id = id;
        this.nameMonth = nameMonth;
        this.shiftHour = shiftHour;
        this.brigade = brigade;
    }

    public Schedule(String nameMonth, ShiftHour shiftHour, Brigade brigade) {
        this.nameMonth = nameMonth;
        this.shiftHour = shiftHour;
        this.brigade = brigade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameMonth() {
        return nameMonth;
    }

    public void setNameMonth(String nameMonth) {
        this.nameMonth = nameMonth;
    }

    public ShiftHour getShiftHour() {
        return shiftHour;
    }

    public void setShiftHour(ShiftHour shiftHour) {
        this.shiftHour = shiftHour;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", nameMonth='" + nameMonth + '\'' +
                ", shiftHour=" + shiftHour +
                ", brigade=" + brigade +
                '}';
    }
}
