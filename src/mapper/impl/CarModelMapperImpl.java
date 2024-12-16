package mapper.impl;

import dto.carDto.CarModelDto;
import entity.car.CarModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CarModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class CarModelMapperImpl implements CarModelMapper {

    private static CarModelMapperImpl INSTANCE;

    public static synchronized CarModelMapperImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarModelMapperImpl();
        }
        return INSTANCE;
    }

    @Override
    public CarModel toEntity(CarModelDto dto) {
        return CarModel
                .builder()
                .modelCarId(dto.getModelCarId())
                .model(dto.getModel())
                .build();
    }

    @Override
    public CarModelDto toDto(CarModel entity) {
        return CarModelDto
                .builder()
                .modelCarId(entity.getModelCarId())
                .model(entity.getModel())
                .build();
    }
}
