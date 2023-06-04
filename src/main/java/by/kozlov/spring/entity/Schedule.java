package by.kozlov.spring.entity;

import lombok.*;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedule",schema = "public")
public class Schedule implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_month")
    private String nameMonth;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hour")
    private ShiftHour shiftHour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brigade")
    private Brigade brigade;

    public void setShiftHour(ShiftHour shiftHour) {
        this.shiftHour = shiftHour;
        this.shiftHour.getSchedules().add(this);
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
        this.brigade.getSchedules().add(this);
    }


}
