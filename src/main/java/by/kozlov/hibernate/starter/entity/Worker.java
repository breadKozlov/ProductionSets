package by.kozlov.hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
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
}
