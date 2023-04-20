package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Brigade;
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
    private Brigade brigade;
    private String email;
}
