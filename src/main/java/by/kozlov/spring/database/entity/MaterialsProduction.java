package by.kozlov.spring.database.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "production_materials")
public class MaterialsProduction implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_set_material")
    private Material material;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brigade")
    private Brigade brigade;
    @Column(name = "quantity_of_raw_materials")
    private Double quantity;
    @Column(name = "date_of_production")
    private LocalDate dateOfProduction;

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
        this.brigade.getMaterialsProduction().add(this);
    }

    public void setMaterial(Material material) {
        this.material = material;
        this.material.getMaterialsProduction().add(this);
    }
}
