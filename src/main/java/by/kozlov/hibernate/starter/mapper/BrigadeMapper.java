package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.BrigadeDto;
import by.kozlov.hibernate.starter.entity.Brigade;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class BrigadeMapper implements Mapper<Brigade, BrigadeDto>{
    @Override
    public BrigadeDto mapFrom(Brigade object) {
        return BrigadeDto.builder()
                .id(object.getId())
                .nameOfBrigade(object.getNameOfBrigade())
                .phoneNumberOfForeman(object.getPhoneNumberOfForeman())
                .build();
    }
}
