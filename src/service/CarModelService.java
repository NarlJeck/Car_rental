package service;


import dto.carDto.CarModelDto;

import java.util.List;

public interface CarModelService {
    CarModelDto findById(Long id);

    List<CarModelDto> findAll();


}
