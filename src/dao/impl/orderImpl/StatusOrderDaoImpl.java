package dao.impl.orderImpl;

import dao.orderDao.StatusOrderDao;
import entity.order.StatusOrder;
import exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatusOrderDaoImpl implements StatusOrderDao {

    private static final String STATUS_ORDER_ID ="status_order_id";
    private static final String STATUS_ORDER ="status_order";


    private static StatusOrderDaoImpl INSTANCE;

    public static StatusOrderDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StatusOrderDaoImpl();
        }
        return INSTANCE;
    }
    private static final String CREATE_SQL = "INSERT INTO status_order(status_order) VALUES (?)";

    private static final String FIND_BY_ID_SQL = "SELECT status_order_id," +
            " status_order.status_order " +
            "FROM status_order WHERE status_order_id = ?";

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public StatusOrder create(StatusOrder statusOrder) {

        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, statusOrder.getStatusOrder());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                statusOrder.setStatusOrderId(generatedKeys.getLong(STATUS_ORDER_ID));
            }
            return statusOrder;

        } catch (SQLException e) {
            throw new DaoException("DaoException statusOrder - method create",e);
        }
    }

    @Override
    public void update(StatusOrder entity) {

    }

    @Override
    public Optional<StatusOrder> findById(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException("DaoException StatusOrder - method(findById)", e);
        }
    }

    public Optional<StatusOrder> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            StatusOrder statusOrder = null;
            if (resultSet.next()) {
                statusOrder = new StatusOrder(
                        resultSet.getLong(STATUS_ORDER_ID),
                        resultSet.getString(STATUS_ORDER)
                );
            }
            return Optional.ofNullable(statusOrder);
        } catch (SQLException e) {
            throw new DaoException("Exception StatusOrder - method(findById), Connection", e);
        }

    }

    @Override
    public List<StatusOrder> findAll() {
        return null;
    }
}
