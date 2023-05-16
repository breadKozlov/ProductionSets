package by.kozlov.hibernate.starter.dto;

import by.kozlov.hibernate.starter.entity.Brigade;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkerDto {
    private Integer id;
    private String nameOfWorker;
    private String surnameOfWorker;
    private String speciality;
    private Integer rank;
    private Integer experience;
    private BrigadeDto brigade;
    private String email;
}
