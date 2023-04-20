package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Set;
import by.kozlov.jdbc.starter.entity.Worker;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkersSetsDto {

    private Integer id;
    private Set set;
    private Worker worker;
    private Integer requirement;
}
