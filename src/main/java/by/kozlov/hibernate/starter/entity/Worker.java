package by.kozlov.hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@ToString(exclude = "productions")
@EqualsAndHashCode(exclude = "productions")
@Table (name = "workers", schema = "public")
public class Worker {

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
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "worker")
    private List<Production> productions = new ArrayList<>();


}
