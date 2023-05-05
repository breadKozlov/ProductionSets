package by.kozlov.hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@ToString(exclude = "workers")
@EqualsAndHashCode(exclude = "workers")
@Table(name="brigades",schema = "public")
public class Brigade {

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

    public void addWorker(Worker worker) {
        workers.add(worker);
        worker.setBrigade(this);
    }
}
