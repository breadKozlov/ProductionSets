package by.kozlov.jdbc.starter.mapper;

import by.kozlov.jdbc.starter.dto.BrigadeDto;
import by.kozlov.jdbc.starter.entity.Brigade;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrigadeMapper implements Mapper<Brigade, BrigadeDto>{

    private static final BrigadeMapper INSTANCE = new BrigadeMapper();
    @Override
    public BrigadeDto mapFrom(Brigade object) {
        return BrigadeDto.builder()
                .id(object.getId())
                .nameOfBrigade(object.getNameOfBrigade())
                .phoneNumberOfForeman(object.getPhoneNumberOfForeman())
                .build();
    }

    public static BrigadeMapper getInstance() {
        return INSTANCE;
    }
}
