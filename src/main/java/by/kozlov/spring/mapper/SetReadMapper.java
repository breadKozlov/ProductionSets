package by.kozlov.spring.mapper;

import by.kozlov.spring.database.entity.Set;
import by.kozlov.spring.dto.SetReadDto;
import org.springframework.stereotype.Component;

@Component
public class SetReadMapper implements Mapper<Set, SetReadDto> {
    @Override
    public SetReadDto map(Set object) {
        return SetReadDto.builder()
                .id(object.getId())
                .nameOfSet(object.getNameOfSet())
                .numberOfPartsIncluded(object.getNumberOfPartsIncluded())
                .rateOfSet(object.getRateOfSet())
                .build();
    }
}
