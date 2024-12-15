package dto.carDto;

import entity.car.CarColor;
import entity.car.CarModel;
import entity.car.CarStatus;
import entity.car.CarTyp;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString

public class CarDto {
    private Long carId;

    private LocalDateTime year;

    private Integer numberSeats;

    private BigDecimal rentalPricePerDay;

    private String registrationNumber;

    private CarColor carColor;

    private CarModel modelCar;

    private CarStatus statusCar;

    private CarTyp typCar;
}
