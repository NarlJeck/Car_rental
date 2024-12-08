package dto.carDto;

import entity.car.CarColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarDto {
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
