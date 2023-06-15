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
@EqualsAndHashCode(exclude = {"requirements","materialsProduction"})
@ToString(exclude = {"requirements","materialsProduction"})
@Table(name = "set_materials")
public class Material implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_of_material")
    private String nameOfMaterial;
    private String description;
    @Builder.Default
    @OneToMany(mappedBy = "material")
    private List<Requirement> requirements = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "material")
    private List<MaterialsProduction> materialsProduction = new ArrayList<>();

}
