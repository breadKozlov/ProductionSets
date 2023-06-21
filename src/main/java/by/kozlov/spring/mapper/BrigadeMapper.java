package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.BrigadeDto;
import by.kozlov.spring.database.entity.Brigade;
import org.springframework.stereotype.Component;

@Component
public class BrigadeMapper implements OlderMapper<Brigade, BrigadeDto> {
    @Override
    public BrigadeDto mapFrom(Brigade object) {
        return BrigadeDto.builder()
                .id(object.getId())
                .nameOfBrigade(object.getNameOfBrigade())
                .phoneNumberOfForeman(object.getPhoneNumberOfForeman())
                .build();
    }
}
