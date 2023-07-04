package by.kozlov.spring.database.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@ToString(exclude = {"productions","workersSets"})
@EqualsAndHashCode(exclude = {"productions","workersSets"})
@Table (name = "workers")
public class Worker implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_worker")
    private String nameOfWorker;
    @Column(name = "surname_worker")
    private String surnameOfWorker;
    private String speciality;
    private Integer rank;
    private Integer experience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_number")
    private Brigade brigade;
    @Column(nullable = false, unique = true)
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "worker",cascade = CascadeType.ALL)
    private List<Production> productions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "worker",cascade = CascadeType.ALL)
    private List<WorkersSets> workersSets = new ArrayList<>();

}
