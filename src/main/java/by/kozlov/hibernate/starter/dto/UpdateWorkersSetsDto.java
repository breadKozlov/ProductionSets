package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdateWorkersSetsDto {

    private String id;
    private String set;
    private String worker;
    private String requirement;
}
