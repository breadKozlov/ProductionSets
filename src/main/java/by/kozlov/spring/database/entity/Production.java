package by.kozlov.spring.database.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "production")
public class Production implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker worker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_set")
    private Set set;
    @Column(name = "made_sets")
    private Integer madeSets;
    @Column(name = "date_of_production")
    private LocalDate dateOfProduction;

    public void setSet(Set set) {
        this.set = set;
        this.set.getProductions().add(this);
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
        this.worker.getProductions().add(this);
    }

    public String fullName() {
        return worker.getNameOfWorker() + " " + worker.getSurnameOfWorker() + " "
                + set.getNameOfSet() + " " + madeSets;
    }
}
