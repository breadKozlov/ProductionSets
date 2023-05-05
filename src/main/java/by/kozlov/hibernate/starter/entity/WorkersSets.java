package by.kozlov.hibernate.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkersSets {

    private Integer id;
    private Set set;
    private Worker worker;
    private Integer requirement;
}
