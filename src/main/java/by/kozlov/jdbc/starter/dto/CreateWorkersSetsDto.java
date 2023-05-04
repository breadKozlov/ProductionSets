package by.kozlov.jdbc.starter.dto;

import by.kozlov.jdbc.starter.entity.Set;
import by.kozlov.jdbc.starter.entity.Worker;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateWorkersSetsDto {

    private String set;
    private String worker;
    private String requirement;
}
