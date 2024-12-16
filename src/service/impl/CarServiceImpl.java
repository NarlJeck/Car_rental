package service.impl;

import dao.carDao.CarDao;
import dao.impl.carImpl.CarDaoImpl;
import dto.carDto.CarDto;
import mapper.CarMapper;
import mapper.impl.CarMapperImpl;
import service.CarService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CarServiceImpl implements CarService {

    private static CarServiceImpl INSTANCE;

    private final CarDao carDao = CarDaoImpl.getInstance();

    private final CarMapper carMapper = CarMapperImpl.getInstance();

    public static synchronized CarServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarServiceImpl();
        }
        return INSTANCE;
    }

    private CarServiceImpl() {
    }

    @Override
    public CarDto findById(Long id) {
        return carDao.findById(id)
                .map(carMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find car by id"));
    }

    @Override
    public List<CarDto> findAll() {

        return carDao.findAll()
                .stream()
                .map(carMapper::toDto)
                .collect(toList());
    }

    @Override
    public void update(CarDto carDto) {

    }

    @Override
    public boolean delete(CarDto carDto) {
        return false;
    }

    @Override
    public CarDto create(CarDto carDto) {
        return null;
    }

    @Override
    public List<CarDto> findByModel(String model) {
      return   carDao.findByModel(model)
              .stream()
              .map(carMapper::toDto)
              .collect(toList());
    }


}
