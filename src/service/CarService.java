package service;

import dto.carDto.CarDto;

import java.util.List;

public interface CarService {

    CarDto findById(Long id);

    List<CarDto> findAll();

    void update(CarDto carDto);

    boolean delete(CarDto carDto);

    CarDto create(CarDto carDto);

    List<CarDto> findByModel(String model);



}
