package by.kozlov.hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@ToString(exclude = {"schedules","workers","materialsProduction"})
@EqualsAndHashCode(exclude = {"schedules","workers","materialsProduction"})
@Table(name="brigades",schema = "public")
public class Brigade implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_of_brigade")
    private String nameOfBrigade;
    @Column(name = "phone_number_of_foreman")
    private String phoneNumberOfForeman;
    @Builder.Default
    @OneToMany(mappedBy = "brigade", cascade = CascadeType.ALL)
    private Set<Worker> workers = new HashSet<>();
    @Builder.Default
    @OneToMany(mappedBy = "brigade")
    private List<MaterialsProduction> materialsProduction = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "brigade")
    private List<Schedule> schedules = new ArrayList<>();

    public void addWorker(Worker worker) {
        workers.add(worker);
        worker.setBrigade(this);
    }
}
