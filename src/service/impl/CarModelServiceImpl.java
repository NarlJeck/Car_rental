package service.impl;

import dao.carDao.CarModelDao;
import dao.impl.carImpl.CarModelDaoImpl;
import dto.carDto.CarModelDto;
import exception.ServiceException;
import mapper.CarModelMapper;
import mapper.impl.CarModelMapperImpl;
import service.CarModelService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CarModelServiceImpl implements CarModelService {

   private static CarModelServiceImpl INSTANCE;

   private final CarModelDao carModelDao = CarModelDaoImpl.getInstance();

   private final CarModelMapper carModelMapper = CarModelMapperImpl.getInstance();

    public static synchronized CarModelServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarModelServiceImpl();
        }
        return INSTANCE;
    }

    private CarModelServiceImpl(){

    }
    @Override
    public CarModelDto findById(Long id) {
        return carModelDao.findById(id)
                .map(carModelMapper::toDto)
                .orElseThrow(() -> new ServiceException("Can not find car by id"));
    }

    @Override
    public List<CarModelDto> findAll() {
        return carModelDao.findAll()
                .stream()
                .map(carModelMapper::toDto)
                .collect(toList());
    }
}
