package dao.impl.carImpl;

import dao.carDao.CarDao;
import entity.car.Car;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private static final String CAR_ID = "car_id";
    private static final String YEAR = "year";
    private static final String NUMBER_SEATS = "number_seats";
    private static final String RENTAL_PRICE_PER_DAY = "rental_price_per_day";
    private static final String REGISTRATION_NUMBER = "registration_number";
    private static final String COLOR_CAR_ID = "color_id";
    private static final String MODEL_ID = "model_id";
    private static final String STATUS_ID = "status_id";
    private static final String TYPE_ID = "type_id";

    private static CarDaoImpl INSTANCE;

    private CarDaoImpl() {
    }

    private static final String DELETE_SQL = "DELETE FROM car WHERE car_id = ?";
    private static final String CREATE_SQL = "INSERT INTO car(" +
            "year," +
            " number_seats, " +
            "rental_price_per_day, " +
            "registration_number, " +
            "color_id, " +
            "model_id," +
            " status_id, " +
            "type_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE car " +
            "SET year = ?," +
            "number_seats = ?," +
            "rental_price_per_day = ?," +
            "registration_number = ?," +
            "color_id = ?," +
            "status_id = ?," +
            "type_id = ?" +
            "WHERE car_id = ?";

    private static final String FIND_ALL_SQL = "SELECT car_id," +
            " year," +
            "number_seats," +
            "rental_price_per_day," +
            "registration_number," +
            "color_id," +
            "model_id," +
            "status_id," +
            "type_id " +
            "FROM car";

    private static final String FIND_BY_ID_SQL =
            "SELECT car_id," +
                    " year," +
                    "number_seats," +
                    "rental_price_per_day," +
                    "registration_number," +
                    "color_id," +
                    "model_id," +
                    "status_id," +
                    "type_id " +
                    "FROM car WHERE car_id =?";

    private static final String FIND_ALL_CAR_BY_MODEl =
            "SELECT car_id," +
                    " year," +
                    "number_seats," +
                    "rental_price_per_day," +
                    "registration_number," +
                    "color_id," +
                    "model_id," +
                    "status_id," +
                    "type_id " +
                    "FROM car " +
                    "JOIN model_car ON model_id=model_car_id " +
                    "WHERE model_car.model=?";


    private final CarColorDaoImpl carColorDao = CarColorDaoImpl.getInstance();
    private final CarModelDaoImpl carModelDao = CarModelDaoImpl.getInstance();
    private final CarStatusDaoImpl carStatusDao = CarStatusDaoImpl.getINSTANCE();
    private final CarTypDaoImpl carTypDao = CarTypDaoImpl.getInstance();

    public Car create(Car car) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(car.getYear()));
            preparedStatement.setInt(2, car.getNumberSeats());
            preparedStatement.setBigDecimal(3, car.getRentalPricePerDay());
            preparedStatement.setString(4, car.getRegistrationNumber());
            preparedStatement.setLong(5, car.getCarColor().getColorCarId());
            preparedStatement.setLong(6, car.getModelCar().getModelCarId());
            preparedStatement.setLong(7, car.getStatusCar().getStatusCarId());
            preparedStatement.setLong(8, car.getTypCar().getTypeCarId());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                car.setCarId(generatedKeys.getLong(CAR_ID));
            }
            return car;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public void update(Car car) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(car.getYear()));
            preparedStatement.setInt(2, car.getNumberSeats());
            preparedStatement.setBigDecimal(3, car.getRentalPricePerDay());
            preparedStatement.setString(4, car.getRegistrationNumber());
            preparedStatement.setLong(5, car.getCarColor().getColorCarId());
            preparedStatement.setLong(6, car.getModelCar().getModelCarId());
            preparedStatement.setLong(7, car.getStatusCar().getStatusCarId());
            preparedStatement.setLong(8, car.getTypCar().getTypeCarId());
            preparedStatement.setLong(9, car.getCarId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public Optional<Car> findById(Long carId) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(carId, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Car> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            Car car = null;
            if (resultSet.next()) {
                car = new Car(
                        resultSet.getLong(CAR_ID),
                        resultSet.getTimestamp(YEAR).toLocalDateTime(),
                        resultSet.getInt(NUMBER_SEATS),
                        resultSet.getBigDecimal(RENTAL_PRICE_PER_DAY),
                        resultSet.getString(REGISTRATION_NUMBER),
                        carColorDao.findById(resultSet.getLong(COLOR_CAR_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        carModelDao.findById(resultSet.getLong(MODEL_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        carStatusDao.findById(resultSet.getLong(STATUS_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        carTypDao.findById(resultSet.getLong(TYPE_ID),
                                resultSet.getStatement().getConnection()).orElse(null)
                );

            }
            return Optional.ofNullable(car);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public List<Car> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(buildCar(resultSet));
            }
            return cars;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Car buildCar(ResultSet resultSet) throws SQLException {

        return new Car(
                resultSet.getLong(CAR_ID),
                resultSet.getTimestamp(YEAR).toLocalDateTime(),
                resultSet.getInt(NUMBER_SEATS),
                resultSet.getBigDecimal(RENTAL_PRICE_PER_DAY),
                resultSet.getString(REGISTRATION_NUMBER),
                carColorDao.findById(resultSet.getLong(COLOR_CAR_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                carModelDao.findById(resultSet.getLong(MODEL_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                carStatusDao.findById(resultSet.getLong(STATUS_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                carTypDao.findById(resultSet.getLong(TYPE_ID),
                        resultSet.getStatement().getConnection()).orElse(null)
        );
    }

    public static synchronized CarDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarDaoImpl();
        }

        return INSTANCE;
    }


    @Override
    public List<Car> findByModel(String model) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_CAR_BY_MODEl)) {
            prepareStatement.setString(1, model);

            ResultSet resultSet = prepareStatement.executeQuery();
            List<Car> carsId = new ArrayList<>();
            Car car = null;
            if (resultSet.next()) {
                car = new Car(
                        resultSet.getLong(CAR_ID),
                        resultSet.getTimestamp(YEAR).toLocalDateTime(),
                        resultSet.getInt(NUMBER_SEATS),
                        resultSet.getBigDecimal(RENTAL_PRICE_PER_DAY),
                        resultSet.getString(REGISTRATION_NUMBER),
                        carColorDao.findById(resultSet.getLong(COLOR_CAR_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        carModelDao.findById(resultSet.getLong(MODEL_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        carStatusDao.findById(resultSet.getLong(STATUS_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        carTypDao.findById(resultSet.getLong(TYPE_ID),
                                resultSet.getStatement().getConnection()).orElse(null));
                carsId.add(car);

            }
            return carsId;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
