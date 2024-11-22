package dao.impl.carImpl;

import dao.carDao.CarModelDao;
import entity.car.CarColor;
import entity.car.CarModel;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarModelDaoImpl implements CarModelDao {

    private static final String MODEL_CAR_ID = "model_car_id";
    private static final String MODEL = "model";

    private static final CarModelDaoImpl INSTANCE = new CarModelDaoImpl();

    private CarModelDaoImpl() {
    }

    private static final String CREATE_SQL = "INSERT INTO model_car(model) VALUES (?)";
    private static final String FIND_BY_ID_SQL = "SELECT model_car_id," +
            "model " +
            "FROM model_car " +
            "WHERE model_car_id = ?";
    private static final String DELETE_SQL = "DELETE FROM model_car WHERE model_car_id = ?";

    private static final String UPDATE_SQL = "UPDATE model_car SET model = ? WHERE model_car_id = ?";
    private static final String FIND_ALL_SQL = "SELECT model_car_id, model FROM model_car";


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
    public CarModel create(CarModel carModel) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carModel.getModel());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                carModel.setModelCarId(generatedKeys.getLong(MODEL_CAR_ID));
            }
            return carModel;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(CarModel carModel) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, carModel.getModel());
            preparedStatement.setLong(2, carModel.getModelCarId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<CarModel> findById(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<CarModel> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            CarModel carModel = null;
            if (resultSet.next()) {
                carModel = new CarModel(
                        resultSet.getLong(MODEL_CAR_ID),
                        resultSet.getString(MODEL)
                );
            }
            return Optional.ofNullable(carModel);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<CarModel> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<CarModel> carModels = new ArrayList<>();
            while (resultSet.next()) {
                carModels.add(buildCarModel(resultSet));
            }
            return carModels;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private CarModel buildCarModel(ResultSet resultSet) throws SQLException {
        return new CarModel(
                resultSet.getLong(MODEL_CAR_ID),
                resultSet.getString(MODEL)
        );
    }

    public static CarModelDaoImpl getInstance() {
        return INSTANCE;
    }
}
