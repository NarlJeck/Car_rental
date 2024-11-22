package dao.impl.clientImpl;

import dao.clientDao.PassportDao;
import entity.client.Passport;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassportDaoImpl implements PassportDao {

    private PassportDaoImpl() {
    }

    private static PassportDaoImpl INSTANCE;

    private static final String PASSPORT_ID = "passport_id";
    private static final String SERIAL_NUMBER = "serial_number";
    private static final String EXPIRED_DATE = "expired_date";

    private static final String DELETE_SQL = "DELETE FROM passport WHERE passport_id = ?";

    private static final String CREATE_SQL = "INSERT INTO passport(" +
            "serial_number," +
            " expired_date) " +
            "VALUES (?, ?)";

    private static final String UPDATE_SQL = "UPDATE passport " +
            "SET serial_number = ?," +
            "expired_date = ?" +
            "WHERE passport_id = ?";

    private static final String FIND_ALL_SQL = "SELECT passport_id ," +
            " serial_number," +
            "expired_date " +
            "FROM passport";

    private static final String FIND_BY_ID_SQL =
            "SELECT passport_id," +
                    " serial_number," +
                    "expired_date " +
                    "FROM passport WHERE passport_id =?";

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception PassportDao - method(delete)", e);
        }
    }

    @Override
    public Passport create(Passport passport) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passport.getSerialNumber());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(passport.getExpiredData()));

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                passport.setPassportId(generatedKeys.getLong(PASSPORT_ID));
            }
            return passport;

        } catch (SQLException e) {
            throw new DaoException("Exception PassportDao - method(create)", e);
        }
    }

    @Override
    public void update(Passport passport) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, passport.getSerialNumber());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(passport.getExpiredData()));
            preparedStatement.setLong(3, passport.getPassportId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Exception PassportDao - method(update)", e);
        }
    }

    @Override
    public Optional<Passport> findById(Long passportId) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(passportId, connection);
        } catch (SQLException e) {
            throw new DaoException("Exception PassportDao - method(findById)", e);
        }
    }

    public Optional<Passport> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            Passport passport = null;
            if (resultSet.next()) {
                passport = new Passport(
                        resultSet.getLong(PASSPORT_ID),
                        resultSet.getString(SERIAL_NUMBER),
                        resultSet.getTimestamp(EXPIRED_DATE).toLocalDateTime()
                );
            }
            return Optional.ofNullable(passport);
        } catch (SQLException e) {
            throw new DaoException("Exception PassportDao - method(findById), Connection", e);
        }

    }

    private Passport buildPassport(ResultSet resultSet) throws SQLException {
        return new Passport(
                resultSet.getLong(PASSPORT_ID),
                resultSet.getString(SERIAL_NUMBER),
                resultSet.getTimestamp(EXPIRED_DATE).toLocalDateTime()
        );
    }

    @Override
    public List<Passport> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<Passport> passports = new ArrayList<>();
            while (resultSet.next()) {
                passports.add(buildPassport(resultSet));
            }
            return passports;
        } catch (SQLException e) {
            throw new DaoException("Exception PassportDao - method(findAll)", e);
        }
    }

    public static PassportDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PassportDaoImpl();
        }

        return INSTANCE;
    }
}




