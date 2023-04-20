package by.kozlov.jdbc.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Worker {

    private Integer id;
    private String nameOfWorker;
    private String surnameOfWorker;
    private String speciality;
    private Integer rank;
    private Integer experience;
    private Brigade brigade;
    private String email;
}
