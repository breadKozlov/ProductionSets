package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkersSetsReadDto {
    Integer id;
    SetReadDto set;
    WorkerReadDto worker;
    Integer requirement;
}
