package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdateWorkersSetsDto {

    String id;
    @NotEmpty
    String set;
    @NotEmpty
    String worker;
    @NotEmpty
    String requirement;
}
