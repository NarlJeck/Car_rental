package mapper.impl;

import dto.carDto.CarDto;
import entity.car.Car;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CarMapper;
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class CarMapperImpl implements CarMapper {

    private static CarMapperImpl INSTANCE;

    public static synchronized CarMapperImpl getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CarMapperImpl();
        }
        return INSTANCE;
    }

    @Override
    public Car toEntity(CarDto dto) {
        return Car
                .builder()
                .carId(dto.getCarId())
                .year(dto.getYear())
                .numberSeats(dto.getNumberSeats())
                .rentalPricePerDay(dto.getRentalPricePerDay())
                .registrationNumber(dto.getRegistrationNumber())
                .carColor(dto.getCarColor())
                .modelCar(dto.getModelCar())
                .statusCar(dto.getStatusCar())
                .typCar(dto.getTypCar())
                .build();

    }

    @Override
    public CarDto toDto(Car entity) {
        return CarDto
                .builder()
                .carId(entity.getCarId())
                .year(entity.getYear())
                .numberSeats(entity.getNumberSeats())
                .rentalPricePerDay(entity.getRentalPricePerDay())
                .registrationNumber(entity.getRegistrationNumber())
                .carColor(entity.getCarColor())
                .modelCar(entity.getModelCar())
                .statusCar(entity.getStatusCar())
                .typCar(entity.getTypCar())
                .build();
    }
}
