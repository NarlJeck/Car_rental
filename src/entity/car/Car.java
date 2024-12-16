package entity.car;

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


public class Car {

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
