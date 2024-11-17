package dao;

import entity.car.Car;
import exception.DaoException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class CarDao implements Dao<Long, Car> {

    private static final CarDao INSTANCE = new CarDao();

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
            "model_id = ?," +
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

    private final CarColorDao colorCarDao = CarColorDao.getInstance();

    public Car create(Car car) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(car.getYear()));
            preparedStatement.setInt(2, car.getNumberSeats());
            preparedStatement.setBigDecimal(3, car.getRentalPricePerDay());
            preparedStatement.setString(4, car.getRegistrationNumber());
            preparedStatement.setLong(5, car.getCarColor().getColorCarId());
            preparedStatement.setLong(6, car.getModelCarId());
            preparedStatement.setLong(7, car.getStatusCarId());
            preparedStatement.setLong(8, car.getTypCarId());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                car.setCarId(generatedKeys.getLong("car_id"));
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
            preparedStatement.setLong(6, car.getModelCarId());
            preparedStatement.setLong(7, car.getStatusCarId());
            preparedStatement.setLong(8, car.getTypCarId());
            preparedStatement.setLong(9, car.getCarId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    public Optional<Car> findById(Long carId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, carId);

            var resultSet = preparedStatement.executeQuery();
            Car car = null;
            if (resultSet.next()) {
                car = buildCar(resultSet);
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
    private Car buildCar(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getLong("car_id"),
                resultSet.getTimestamp("year").toLocalDateTime(),
                resultSet.getInt("number_seats"),
                resultSet.getBigDecimal("rental_price_per_day"),
                resultSet.getString("registration_number"),
                colorCarDao.findById(resultSet.getLong("color_car_id"),
                resultSet.getStatement().getConnection()).orElse(null),
                resultSet.getLong("model_id"),
                resultSet.getLong("status_id"),
                resultSet.getLong("type_id")
        );
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }
}
