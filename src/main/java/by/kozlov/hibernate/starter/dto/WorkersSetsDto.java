package by.kozlov.hibernate.starter.dto;

import by.kozlov.hibernate.starter.entity.Set;
import by.kozlov.hibernate.starter.entity.Worker;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkersSetsDto {

    private Integer id;
    private SetDto set;
    private WorkerDto worker;
    private Integer requirement;
}
