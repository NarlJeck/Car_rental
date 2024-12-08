package entity.order;

import entity.car.Car;
import entity.client.Client;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

public class OrderRental {

    private Long orderRentalId;
    private Client client;
    private Car car;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private BigDecimal totalRentalCost;
    private Long statusOrderId;

}
