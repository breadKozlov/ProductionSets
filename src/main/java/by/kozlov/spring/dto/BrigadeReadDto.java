package by.kozlov.spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BrigadeReadDto {
    Integer id;
    String nameOfBrigade;
    String phoneNumberOfForeman;
}
