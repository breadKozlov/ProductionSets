package by.kozlov.hibernate.starter.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = {"productions","requirements","workersSets"})
@ToString(exclude = {"productions","requirements","workersSets"})
@Table(name = "sets_for_cars",schema = "public")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Sets")
public class Set implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_of_set")
    private String nameOfSet;
    @Column(name = "num_of_parts")
    private Integer numberOfPartsIncluded;
    @Column(name = "rate_of_set")
    private Double rateOfSet;
    @Builder.Default
    @OneToMany(mappedBy = "set")
    private List<Production> productions = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "set")
    private List<Requirement> requirements = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "set")
    private List<WorkersSets> workersSets = new ArrayList<>();
}
