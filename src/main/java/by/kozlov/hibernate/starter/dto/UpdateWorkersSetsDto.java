package by.kozlov.hibernate.starter.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Builder
@Value
public class UpdateWorkersSetsDto {

    private String id;
    @NotEmpty
    private String set;
    @NotEmpty
    private String worker;
    @NotEmpty
    private String requirement;
}
