package by.kozlov.hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Material {

    private Integer id;
    private String nameOfMaterial;
    private String description;
}
