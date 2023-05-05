package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dto.SetDto;
import by.kozlov.hibernate.starter.entity.Set;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SetMapper implements Mapper<Set, SetDto>{

    private static final SetMapper INSTANCE = new SetMapper();
    @Override
    public SetDto mapFrom(Set object) {
        return SetDto.builder()
                .id(object.getId())
                .nameOfSet(object.getNameOfSet())
                .numberOfPartsIncluded(object.getNumberOfPartsIncluded())
                .rateOfSet(object.getRateOfSet())
                .build();
    }

    public static SetMapper getInstance() {
        return INSTANCE;
    }
}
