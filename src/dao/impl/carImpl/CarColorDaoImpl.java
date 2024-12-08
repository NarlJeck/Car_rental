package dao.impl.carImpl;

import dao.carDao.CarColorDao;
import entity.car.CarColor;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarColorDaoImpl implements CarColorDao {

    private static final String CAR_COLOR_ID = "car_color_id";
    private static final String COLOR = "color";

    private static CarColorDaoImpl INSTANCE;

    private CarColorDaoImpl() {

    }

    private static final String CREATE_SQL = "INSERT INTO car_color(color) VALUES (?)";
    private static final String FIND_BY_ID_SQL = "SELECT car_color_id," +
            "color " +
            "FROM car_color " +
            "WHERE car_color_id = ?";
    private static final String DELETE_SQL = "DELETE FROM car_color WHERE car_color_id = ?";

    private static final String UPDATE_SQL = "UPDATE car_color SET color = ? WHERE car_color = ?";
    private static final String FIND_ALL_SQL = "SELECT car_color_id, color FROM car_color";


    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CarColor create(CarColor carColor) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carColor.getColor());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                carColor.setColorCarId(generatedKeys.getLong(CAR_COLOR_ID));
            }
            return carColor;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(CarColor carColor) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, carColor.getColor());
            preparedStatement.setLong(2, carColor.getColorCarId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public Optional<CarColor> findById(Long id) {
        try (var connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<CarColor> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeQuery();
            CarColor colorCar = null;
            if (resultSet.next()) {
                colorCar = new CarColor(
                        resultSet.getLong(CAR_COLOR_ID),
                        resultSet.getString(COLOR)
                );
            }
            return Optional.ofNullable(colorCar);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<CarColor> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<CarColor> carColors = new ArrayList<>();
            while (resultSet.next()) {
                carColors.add(buildCarColor(resultSet));
            }
            return carColors;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private CarColor buildCarColor(ResultSet resultSet) throws SQLException {
        return new CarColor(
                resultSet.getLong(CAR_COLOR_ID),
                resultSet.getString(COLOR)
        );
    }

    public static synchronized CarColorDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarColorDaoImpl();
        }
        return INSTANCE;
    }
}
