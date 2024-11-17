package entity.car;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class Car {

    private Long carId;

    private LocalDateTime year;

    private Integer numberSeats;

    private BigDecimal rentalPricePerDay;

    private String registrationNumber;

    private CarColor carColor;

    private Long modelCarId;

    private Long statusCarId;

    private Long typCarId;


}
