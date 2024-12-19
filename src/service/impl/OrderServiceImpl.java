package service.impl;

import dao.impl.orderImpl.OrderDaoImpl;
import dao.orderDao.OrderDao;
import dto.orderDto.OrderRentalDto;
import entity.order.OrderRental;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.OrderMapper;
import mapper.impl.OrderMapperImpl;
import service.OrderService;
import validator.ValidatorOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    private static OrderServiceImpl INSTANCE;

    private final ValidatorOrder validatorOrder = ValidatorOrder.getInstance();
    private final OrderMapper orderMapper = OrderMapperImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    public static synchronized OrderServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public OrderRentalDto findById(Long id) {
        return null;
    }

    @Override
    public List<OrderRentalDto> findAll() {
        return null;
    }

    @Override
    public void update(OrderRentalDto orderRentalDto) {

    }

    @Override
    public boolean delete(OrderRentalDto orderRentalDto) {
        return false;
    }

    private Long calculationNumberDaysBooked(LocalDate startDay, LocalDate endDay) {
        return ChronoUnit.DAYS.between(startDay, endDay);
    }

    private BigDecimal calculationTotalCoastRentalCar(Long countDay, BigDecimal rentalPriceDay) {
        BigDecimal bigDecimalCountDay = BigDecimal.valueOf(countDay);
        return bigDecimalCountDay.multiply(rentalPriceDay);

    }


    @Override
    public Long create(OrderRentalDto orderRentalDto) {
        var validResult = validatorOrder.isValid(orderRentalDto);
        var validSelectDate = validatorOrder.isValidSelectDate(orderRentalDto);
        if (!validResult.isValid()) {
            throw new ValidationException(validResult.getErrors());
        } else if (!validSelectDate.isValid()) {
            throw new ValidationException(validSelectDate.getErrors());
        } else {

            OrderRental orderRental = orderMapper.toEntity(orderRentalDto);
            getTotalCost(orderRental);
            orderDao.create(orderRental);
            return orderRental.getOrderRentalId();
        }
    }

    public void getTotalCost(OrderRental orderRental) {
        LocalDate rentalStartDate = orderRental.getRentalStartDate();
        LocalDate rentalEndDate = orderRental.getRentalEndDate();
        BigDecimal rentalPricePerDay = orderRental.getCar().getRentalPricePerDay();
        Long countDayRental = calculationNumberDaysBooked(rentalStartDate, rentalEndDate);
        BigDecimal totalCoastRentalCar = calculationTotalCoastRentalCar(countDayRental, rentalPricePerDay);
        orderRental.setTotalRentalCost(totalCoastRentalCar);

    }
}
