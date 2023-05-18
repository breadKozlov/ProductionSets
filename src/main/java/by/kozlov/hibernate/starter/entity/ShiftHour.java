package by.kozlov.hibernate.starter.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "schedules")
@EqualsAndHashCode(exclude = "schedules")
@Table(name = "schedule_hours")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "ShiftHours")
public class ShiftHour implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_of_hour")
    private String nameOfShiftHour;
    @Builder.Default
    @OneToMany(mappedBy = "shiftHour")
    List<Schedule> schedules = new ArrayList<>();
}
