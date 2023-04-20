package by.kozlov.jdbc.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Production {

    private Integer id;
    private Worker worker;
    private Set set;
    private Integer madeSets;
    private LocalDateTime dateOfProduction;
}
