package by.kozlov.spring.dto;

import lombok.*;
@Value
@Builder
public class WorkerDto {
    Integer id;
    String nameOfWorker;
    String surnameOfWorker;
    String speciality;
    Integer rank;
    Integer experience;
    BrigadeDto brigade;
    String email;
}
