package by.kozlov.jdbc.starter.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BrigadeDto {

    private Integer id;
    private String nameOfBrigade;
    private String phoneNumberOfForeman;

}
