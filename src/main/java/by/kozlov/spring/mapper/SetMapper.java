package by.kozlov.spring.mapper;

import by.kozlov.spring.dto.SetDto;
import by.kozlov.spring.entity.Set;
import org.springframework.stereotype.Component;

@Component
public class SetMapper implements Mapper<Set, SetDto>{

    @Override
    public SetDto mapFrom(Set object) {
        return SetDto.builder()
                .id(object.getId())
                .nameOfSet(object.getNameOfSet())
                .numberOfPartsIncluded(object.getNumberOfPartsIncluded())
                .rateOfSet(object.getRateOfSet())
                .build();
    }
}
