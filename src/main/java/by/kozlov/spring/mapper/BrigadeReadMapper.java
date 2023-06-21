package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Brigade;
import by.kozlov.spring.dto.BrigadeReadDto;
import org.springframework.stereotype.Component;

@Component
public class BrigadeReadMapper implements Mapper<Brigade, BrigadeReadDto> {

    @Override
    public BrigadeReadDto map(Brigade object) {
        return BrigadeReadDto.builder()
                .id(object.getId())
                .nameOfBrigade(object.getNameOfBrigade())
                .phoneNumberOfForeman(object.getPhoneNumberOfForeman())
                .build();
    }
}
