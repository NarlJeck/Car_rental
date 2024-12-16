package dao.impl.carImpl;

import dao.carDao.CarStatusDao;
import entity.car.CarStatus;
import entity.car.CarTyp;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarStatusDaoImpl implements CarStatusDao {
    private static final String STATUS_CAR_ID = "status_car_id";
    private static final String STATUS = "status_car";

    private static CarStatusDaoImpl INSTANCE;

    public static CarStatusDaoImpl getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new CarStatusDaoImpl();
        }
        return INSTANCE;
    }

    private CarStatusDaoImpl() {
    }

    private static final String FIND_ALL_SQL = "SELECT status_car_id, status_car FROM status_car";
    private static final String FIND_BY_ID_SQL = "SELECT status_car_id," +
            "status_car.status_car " +
            "FROM status_car " +
            "WHERE status_car_id = ?";

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public CarStatus create(CarStatus entity) {
        return null;
    }

    @Override
    public void update(CarStatus entity) {

    }

    @Override
    public Optional<CarStatus> findById(Long id) {
        return Optional.empty();
    }

    public Optional<CarStatus> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            CarStatus carStatus = null;
            if (resultSet.next()) {
                carStatus = new CarStatus(
                        resultSet.getLong(STATUS_CAR_ID),
                        resultSet.getString(STATUS)
                );
            }
            return Optional.ofNullable(carStatus);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    private CarStatus buildCarStatus(ResultSet resultSet) throws SQLException {
        return new CarStatus(
                resultSet.getLong(STATUS_CAR_ID),
                resultSet.getString(STATUS)
        );
    }
    @Override
    public List<CarStatus> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<CarStatus> carStatuses = new ArrayList<>();
            while (resultSet.next()) {
                carStatuses.add(buildCarStatus(resultSet));
            }
            return carStatuses;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
