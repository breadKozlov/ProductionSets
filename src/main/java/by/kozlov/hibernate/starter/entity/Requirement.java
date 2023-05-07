package by.kozlov.hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "requirement", schema = "public")
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_set")
    private Set set;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_set_material")
    private Material material;
    @Column(name = "unit_cost")
    private Double unitCost;
    @Column(name = "total_sets")
    private Integer totalSets;

    public void setMaterial(Material material) {
        this.material = material;
        this.material.getRequirements().add(this);
    }

    public void setSet(Set set) {
        this.set = set;
        this.set.getRequirements().add(this);
    }
}
