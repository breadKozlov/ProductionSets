package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkersSetsCreateEditDto {
    @NotEmpty
    Integer setId;
    @NotEmpty
    Integer workerId;
    @NotEmpty
    Integer requirement;
}
