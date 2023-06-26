package by.kozlov.spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WorkersSetsCreateEditDto {
    @NotNull
    Integer setId;
    @NotNull
    Integer workerId;
    @NotNull
    Integer requirement;
}
