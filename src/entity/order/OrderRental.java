package entity.order;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderRental {

    private Long orderRentalId;
    private Long clientId;
    private Long carId;
    private Long statusOrderId;
    private LocalDateTime rentalStartDate;
    private LocalDateTime rentalEndDate;
    private BigDecimal totalRentalCost;


}
