package by.kozlov.spring.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class ProductionCreateEditDto {

    @NotNull
    Integer workerId;
    @NotNull
    Integer setId;
    @NotNull
    Integer madeSets;
    //@NotEmpty
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate dateOfProduction;
}
