package dao.impl.clientImpl;

import dao.clientDao.ClientDao;
import entity.client.Client;
import exception.DaoException;
import lombok.Getter;
import lombok.ToString;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

    private static final String CLIENT_ID = "client_id";
    private static final String FULL_NAME = "full_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String RESIDENTIAL_ADDRESS = "residential_address";
    private static final String EMAIL = "email";
    private static final String ROLE_ID = "role_id";
    private static final String PASSPORT_ID = "passport_id";
    private static final String DRIVER_LICENSE_ID = "driver_license_id";
    private static final String BANK_CARD_ID = "bank_card_id";

    private static ClientDaoImpl INSTANCE;

    private ClientDaoImpl() {
    }

    private final PassportDaoImpl passportDao = PassportDaoImpl.getInstance();
    private final DriverLicenseDaoImpl driverLicenseDao = DriverLicenseDaoImpl.getINSTANCE();
    private final BankCardDaoImpl bankCardDao = BankCardDaoImpl.getInstance();
    private final RoleDaoImpl roleDao = RoleDaoImpl.getInstance();


    private static final String DELETE_SQL = "DELETE FROM client WHERE client_id = ?";

    private static final String CREATE_SQL = "INSERT INTO client(" +
            "full_name," +
            " phone_number, " +
            "email," +
            " residential_address," +
            " role_id," +
            " passport_id,       " +
            " driver_license_id," +
            "bank_card_id)" +
            "VALUES (?,?,?,?,?,?,?,?)";

    private static final String UPDATE_SQL = "UPDATE client " +
            "SET full_name = ?," +
            "phone_number = ?," +
            "email = ?," +
            "residential_address = ?," +
            "role_id = ?," +
            "passport_id = ?," +
            "driver_license_id = ?," +
            "bank_card_id = ?" +
            "WHERE client_id = ?";

    private static final String FIND_ALL_SQL = "SELECT client_id," +
            " full_name," +
            "phone_number," +
            "email," +
            "residential_address," +
            "role_id," +
            "passport_id," +
            "driver_license_id," +
            "bank_card_id " +
            "FROM client";

    private static final String FIND_BY_ID_SQL =
            "SELECT client_id," +
                    " full_name," +
                    "phone_number," +
                    "email," +
                    "residential_address," +
                    "role_id," +
                    "passport_id," +
                    "driver_license_id," +
                    "bank_card_id " +
                    "FROM client WHERE client_id =?";


    @Override
    public boolean delete(Long id) {

        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(DELETE_SQL)) {
            prepareStatement.setLong(1, id);
            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception clientDao - method(delete)", e);
        }

    }

    @Override
    public Client create(Client client) {
        try (var connection = ConnectionManager.get();
             var prepareStation = connection.prepareStatement(CREATE_SQL)) {
            prepareStation.setString(1, client.getFullName());
            prepareStation.setInt(2, client.getPhoneNumber());
            prepareStation.setString(3, client.getEmail());
            prepareStation.setString(4, client.getResidentialAddress());
            prepareStation.setLong(5, client.getRole().getRoleId());
            prepareStation.setLong(6, client.getPassport().getPassportId());
            prepareStation.setLong(7, client.getDriverLicense().getDriverLicenseId());
            prepareStation.setLong(8, client.getBankCard().getBankCardId());

            prepareStation.executeUpdate();

            var generatedKeys = prepareStation.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setClientId(generatedKeys.getLong(CLIENT_ID));
            }
            return client;
        } catch (SQLException e) {
            throw new DaoException("Exception clientDao - method(create)", e);
        }
    }

    @Override
    public void update(Client client) {
        try (var connection = ConnectionManager.get();
             var prepareStation = connection.prepareStatement(UPDATE_SQL)) {
            prepareStation.setString(1, client.getFullName());
            prepareStation.setInt(2, client.getPhoneNumber());
            prepareStation.setString(3, client.getEmail());
            prepareStation.setString(4, client.getResidentialAddress());
            prepareStation.setLong(5, client.getRole().getRoleId());
            prepareStation.setLong(6, client.getPassport().getPassportId());
            prepareStation.setLong(7, client.getDriverLicense().getDriverLicenseId());
            prepareStation.setLong(8, client.getBankCard().getBankCardId());
            prepareStation.setLong(9, client.getClientId());

            prepareStation.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Exception clientDao - method(update)", e);
        }
    }


    @Override
    public Optional<Client> findById(Long id) {

        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException("Exception clientDao - Optional method(findById)", e);
        }
    }

    public Optional<Client> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            Client client = null;
            if (resultSet.next()) {
                client = new Client(
                        resultSet.getLong(CLIENT_ID),
                        resultSet.getString(FULL_NAME),
                        resultSet.getInt(PHONE_NUMBER),
                        resultSet.getString(EMAIL),
                        resultSet.getString(RESIDENTIAL_ADDRESS),
                        roleDao.findById(resultSet.getLong(ROLE_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        passportDao.findById(resultSet.getLong(PASSPORT_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        driverLicenseDao.findById(resultSet.getLong(DRIVER_LICENSE_ID),
                                resultSet.getStatement().getConnection()).orElse(null),
                        bankCardDao.findById(resultSet.getLong(BANK_CARD_ID),
                                resultSet.getStatement().getConnection()).orElse(null)
                );
            }
            return Optional.ofNullable(client);
        } catch (SQLException e) {
            throw new DaoException("Exception clientDao -Optional and Connection method(findById)", e);
        }

    }

    @Override
    public List<Client> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        } catch (SQLException e) {
            throw new DaoException(" Exception clientDao - method(findAll)", e);
        }
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getLong(CLIENT_ID),
                resultSet.getString(FULL_NAME),
                resultSet.getInt(PHONE_NUMBER),
                resultSet.getString(EMAIL),
                resultSet.getString(RESIDENTIAL_ADDRESS),
                roleDao.findById(resultSet.getLong(ROLE_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                passportDao.findById(resultSet.getLong(PASSPORT_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                driverLicenseDao.findById(resultSet.getLong(DRIVER_LICENSE_ID),
                        resultSet.getStatement().getConnection()).orElse(null),
                bankCardDao.findById(resultSet.getLong(BANK_CARD_ID),
                        resultSet.getStatement().getConnection()).orElse(null));
    }

    public static synchronized ClientDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientDaoImpl();
        }
        return INSTANCE;
    }


}
