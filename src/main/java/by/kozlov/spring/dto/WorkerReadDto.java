package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkerReadDto {
    Integer id;
    String nameOfWorker;
    String surnameOfWorker;
    String speciality;
    Integer rank;
    Integer experience;
    BrigadeReadDto brigade;
    String email;
}
