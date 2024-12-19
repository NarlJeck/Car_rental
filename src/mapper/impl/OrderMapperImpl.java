package mapper.impl;

import dto.orderDto.OrderRentalDto;
import entity.car.Car;
import entity.client.Client;
import entity.order.OrderRental;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CarMapper;
import mapper.ClientMapper;
import mapper.OrderMapper;
import util.LocalDateFormatter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapperImpl implements OrderMapper {

    private final ClientMapper clientMapper = ClientMapperImpl.getInstance();
   private final CarMapper carMapper = CarMapperImpl.getInstance();

    private static OrderMapperImpl INSTANCE;

    public static synchronized OrderMapperImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderMapperImpl();
        }
        return INSTANCE;
    }

    BigDecimal converter(String cost){
        BigDecimal bigDecimalValue = null;
        try {
            bigDecimalValue = new BigDecimal(cost);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный формат числа");
        }
        return bigDecimalValue;
    }


    @Override
    public OrderRental toEntity(OrderRentalDto dto) {
        System.out.println("DTO to Entity  __________ " + dto );
        Client client = clientMapper.toEntity(dto.getClient());
        System.out.println("DTOClient  to etityClient _________"+client);

        Car car = carMapper.toEntity(dto.getCar());
        System.out.println("DTOCar  to etityCar _________"+car);

        return OrderRental.builder()
                .orderRentalId(dto.getOrderRentalId())
                .client(clientMapper.toEntity(dto.getClient()))
                .car(carMapper.toEntity(dto.getCar()))
                .rentalStartDate(LocalDate.from(LocalDateFormatter.format(dto.getRentalStartDate())))
                .rentalEndDate(LocalDate.from(LocalDateFormatter.format(dto.getRentalEndDate())))
                .totalRentalCost(converter(dto.getTotalRentalCost()))
                .statusOrder(dto.getStatusOrder())
                .build();
    }

    @Override
    public OrderRentalDto toDto(OrderRental entity) {
        return null;
    }
}
// сделать заполнение заказа на странице order
// принять данные с страницы
// сделать маппер рассчитать стоимость и добавить данные заказа в личный аккаунт пользователя