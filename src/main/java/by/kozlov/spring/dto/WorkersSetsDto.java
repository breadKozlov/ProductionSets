package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkersSetsDto {

    Integer id;
    SetDto set;
    WorkerDto worker;
    Integer requirement;
}
