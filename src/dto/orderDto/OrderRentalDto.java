package dto.orderDto;

import dto.carDto.CarDto;
import dto.clientDto.ClientDto;
import entity.car.Car;
import entity.client.Client;
import entity.order.StatusOrder;
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
    private ClientDto client;
    private CarDto car;
    private String rentalStartDate;
    private String rentalEndDate;
    private String totalRentalCost;
    private StatusOrder statusOrder;
}
