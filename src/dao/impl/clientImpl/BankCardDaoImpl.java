package dao.impl.clientImpl;

import dao.clientDao.BankCardDao;
import entity.client.BankCard;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankCardDaoImpl implements BankCardDao {

    private static final String BANK_CARD_ID = "bank_card_id";
    private static final String SERIAL_NUMBER = "serial_number";
    private static final String EXPIRED_DATE = "expired_date";


    private static BankCardDaoImpl INSTANCE;

    private BankCardDaoImpl() {
    }

    private static final String DELETE_SQL = "DELETE FROM bank_card WHERE bank_card_id = ?";

    private static final String CREATE_SQL = "INSERT INTO bank_card(" +
            "serial_number," +
            " expired_date) " +
            "VALUES (?, ?)";

    private static final String UPDATE_SQL = "UPDATE bank_card " +
            "SET serial_number = ?," +
            "expired_date = ?" +
            "WHERE bank_card_id = ?";

    private static final String FIND_ALL_SQL = "SELECT bank_card_id," +
            " serial_number," +
            "expired_date " +
            "FROM bank_card";

    private static final String FIND_BY_ID_SQL =
            "SELECT bank_card_id," +
                    " serial_number," +
                    "expired_date " +
                    "FROM bank_card WHERE bank_card_id =?";


    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception bankCardDao - method(delete)", e);
        }

    }

    @Override
    public BankCard create(BankCard bankCard) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bankCard.getSerialNumber());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(bankCard.getExpiredData()));

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                bankCard.setBankCardId(generatedKeys.getLong(BANK_CARD_ID));
            }
            return bankCard;

        } catch (SQLException e) {
            throw new DaoException("Exception bankCardDao - method(create)", e);
        }
    }

    @Override
    public void update(BankCard bankCard) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, bankCard.getSerialNumber());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(bankCard.getExpiredData()));
            preparedStatement.setLong(3, bankCard.getBankCardId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Exception bankCardDao - method(update)", e);
        }
    }

    @Override
    public Optional<BankCard> findById(Long bankCardId) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(bankCardId, connection);
        } catch (SQLException e) {
            throw new DaoException("Exception bankCardDao - method(findById)", e);
        }
    }

    public Optional<BankCard> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            BankCard bankCard = null;
            if (resultSet.next()) {
                bankCard = new BankCard(
                        resultSet.getLong(BANK_CARD_ID),
                        resultSet.getString(SERIAL_NUMBER),
                        resultSet.getTimestamp(EXPIRED_DATE).toLocalDateTime()
                );
            }
            return Optional.ofNullable(bankCard);
        } catch (SQLException e) {
            throw new DaoException("Exception bankCardDao - method(findById),Connection", e);
        }

    }

    private BankCard buildBankCard(ResultSet resultSet) throws SQLException {
        return new BankCard(
                resultSet.getLong(BANK_CARD_ID),
                resultSet.getString(SERIAL_NUMBER),
                resultSet.getTimestamp(EXPIRED_DATE).toLocalDateTime());
    }

    @Override
    public List<BankCard> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<BankCard> bankCards = new ArrayList<>();
            while (resultSet.next()) {
                bankCards.add(buildBankCard(resultSet));
            }
            return bankCards;
        } catch (SQLException e) {
            throw new DaoException("Exception bankCardDao - method(findAll)", e);
        }
    }

    public static synchronized BankCardDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankCardDaoImpl();
        }

        return INSTANCE;
    }
}
