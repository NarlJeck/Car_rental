package dao.carDao;

import dao.BaseCrudDao;
import entity.car.Car;
import entity.car.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarDao extends BaseCrudDao<Long, Car> {
    List<Car> findByModel(String model);

}
