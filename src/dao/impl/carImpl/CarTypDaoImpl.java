package dao.impl.carImpl;

import dao.carDao.CarTypDao;
import entity.car.CarTyp;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarTypDaoImpl implements CarTypDao {

    private static final String TYPE_CAR_ID = "type_car_id";
    private static final String TYP = "typ";

    private CarTypDaoImpl() {
    }

    private static CarTypDaoImpl INSTANCE;

    private static final String CREATE_SQL = "INSERT INTO type_car(typ) VALUES (?)";
    private static final String FIND_BY_ID_SQL = "SELECT type_car_id," +
            "typ " +
            "FROM type_car " +
            "WHERE type_car_id = ?";
    private static final String DELETE_SQL = "DELETE FROM type_car WHERE type_car_id = ?";

    private static final String UPDATE_SQL = "UPDATE type_car SET typ = ? WHERE type_car_id = ?";
    private static final String FIND_ALL_SQL = "SELECT type_car_id, typ FROM type_car";


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
    public CarTyp create(CarTyp carTyp) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carTyp.getType());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                carTyp.setTypeCarId(generatedKeys.getLong(TYPE_CAR_ID));
            }
            return carTyp;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(CarTyp carTyp) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, carTyp.getType());
            preparedStatement.setLong(2, carTyp.getTypeCarId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<CarTyp> findById(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public Optional<CarTyp> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            CarTyp carTyp = null;
            if (resultSet.next()) {
                carTyp = new CarTyp(
                        resultSet.getLong(TYPE_CAR_ID),
                        resultSet.getString(TYP)
                );
            }
            return Optional.ofNullable(carTyp);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<CarTyp> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<CarTyp> carTyps = new ArrayList<>();
            while (resultSet.next()) {
                carTyps.add(buildCarTyp(resultSet));
            }
            return carTyps;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private CarTyp buildCarTyp(ResultSet resultSet) throws SQLException {
        return new CarTyp(
                resultSet.getLong(TYPE_CAR_ID),
                resultSet.getString(TYP)
        );
    }

    public CarTypDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarTypDaoImpl();
        }

        return INSTANCE;
    }
}
