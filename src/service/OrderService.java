package service;


import dto.orderDto.OrderRentalDto;

import java.util.List;

public interface OrderService {
    OrderRentalDto findById(Long id);

    List<OrderRentalDto> findAll();

    void update(OrderRentalDto orderRentalDto);

    boolean delete(OrderRentalDto orderRentalDto);

    Long create(OrderRentalDto orderRentalDto);

}
