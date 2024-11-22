package dao.impl.clientImpl;

import dao.clientDao.DriverLicenseDao;
import entity.client.DriverLicense;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverLicenseDaoImpl implements DriverLicenseDao {

    private static final String DRIVER_LICENSE_ID = "driver_license_id";
    private static final String SERIAL_NUMBER = "serial_number";
    private static final String EXPIRED_DATE = "expired_date";


    private static DriverLicenseDaoImpl INSTANCE;

    private DriverLicenseDaoImpl() {
    }

    private static final String DELETE_SQL = "DELETE FROM driver_license WHERE driver_license_id = ?";

    private static final String CREATE_SQL = "INSERT INTO driver_license(" +
            "serial_number," +
            " expired_date) " +
            "VALUES (?, ?)";

    private static final String UPDATE_SQL = "UPDATE driver_license " +
            "SET serial_number = ?," +
            "expired_date = ?" +
            "WHERE driver_license_id = ?";

    private static final String FIND_ALL_SQL = "SELECT driver_license_id," +
            " serial_number," +
            "expired_date " +
            "FROM driver_license";

    private static final String FIND_BY_ID_SQL =
            "SELECT driver_license_id," +
                    " serial_number," +
                    "expired_date " +
                    "FROM driver_license WHERE driver_license_id =?";

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception driveLicenseDao - method(delete)", e);
        }
    }

    @Override
    public DriverLicense create(DriverLicense driverLicense) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, driverLicense.getSerialNumber());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(driverLicense.getExpiredData()));

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                driverLicense.setDriverLicenseId(generatedKeys.getLong(DRIVER_LICENSE_ID));
            }
            return driverLicense;

        } catch (SQLException e) {
            throw new DaoException("Exception driveLicenseDao - method(create)", e);
        }
    }

    @Override
    public void update(DriverLicense driverLicense) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, driverLicense.getSerialNumber());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(driverLicense.getExpiredData()));
            preparedStatement.setLong(3, driverLicense.getDriverLicenseId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Exception driveLicenseDao - method(update)", e);
        }
    }

    @Override
    public Optional<DriverLicense> findById(Long driverLicenseId) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(driverLicenseId, connection);
        } catch (SQLException e) {
            throw new DaoException("Exception driveLicenseDao - method(findById)", e);
        }
    }

    public Optional<DriverLicense> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            DriverLicense driverLicense = null;
            if (resultSet.next()) {
                driverLicense = new DriverLicense(
                        resultSet.getLong(DRIVER_LICENSE_ID),
                        resultSet.getString(SERIAL_NUMBER),
                        resultSet.getTimestamp(EXPIRED_DATE).toLocalDateTime()
                );
            }
            return Optional.ofNullable(driverLicense);
        } catch (SQLException e) {
            throw new DaoException("Exception driveLicenseDao - method(findById),Connection", e);
        }

    }


    private DriverLicense buildDriverLicense(ResultSet resultSet) throws SQLException {
        return new DriverLicense(
                resultSet.getLong(DRIVER_LICENSE_ID),
                resultSet.getString(SERIAL_NUMBER),
                resultSet.getTimestamp(EXPIRED_DATE).toLocalDateTime()
        );
    }

    @Override
    public List<DriverLicense> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<DriverLicense> driverLicenses = new ArrayList<>();
            while (resultSet.next()) {
                driverLicenses.add(buildDriverLicense(resultSet));
            }
            return driverLicenses;
        } catch (SQLException e) {
            throw new DaoException("Exception driveLicenseDao - method(findAll)", e);
        }
    }

    public static DriverLicenseDaoImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DriverLicenseDaoImpl();
        }

        return INSTANCE;
    }
}
