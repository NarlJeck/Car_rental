package dto.orderDto;

import entity.car.Car;
import entity.client.Client;
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
public class OrderRentalDto {
    private Long orderRentalId;
    private Client client;
    private Car car;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private BigDecimal totalRentalCost;
    private Long statusOrderId;
}
