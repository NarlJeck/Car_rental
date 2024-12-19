package dao.impl.orderImpl;

import dao.impl.carImpl.CarDaoImpl;
import dao.impl.clientImpl.ClientDaoImpl;
import dao.orderDao.OrderDao;
import entity.order.OrderRental;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private OrderDaoImpl() {
    }

    private static final String ORDER_RENTAL_ID = "order_rental_id";
    private static final String ID_CLIENT = "id_client";
    private static final String ID_CAR = "id_car";
    private static final String RENTAL_START_DATE = "rental_start_date";
    private static final String RENTAL_END_DATE = "rental_end_date";
    private static final String TOTAL_RENTAL_COST = "total_rental_cost";
    private static final String STATUS_ORDER_ID = "status_order_id";


    private static OrderDaoImpl INSTANCE;

    private final CarDaoImpl carDaoImpl = CarDaoImpl.getInstance();
    private final ClientDaoImpl clientDaoImpl = ClientDaoImpl.getInstance();
    private final StatusOrderDaoImpl statusOrderDao = StatusOrderDaoImpl.getInstance();

    private static final String DELETE_SQL = "DELETE FROM order_rental WHERE order_rental_id = ?";
    private static final String CREATE_SQL = "INSERT INTO order_rental(" +
            "id_client," +
            " id_car, " +
            "rental_start_date, " +
            "rental_end_date, " +
            "total_rental_cost, " +
            "status_order_id )" +
            "VALUES (?, ?, ?, ?, ?,?)";

    private static final String UPDATE_SQL = "UPDATE order_rental " +
            "SET id_client = ?," +
            "id_car = ?," +
            "rental_start_date = ?," +
            "rental_end_date = ?," +
            "total_rental_cost = ?," +
            "status_order_id = ? " +
            "WHERE order_rental_id = ?";

    private static final String FIND_ALL_SQL = "SELECT order_rental_id," +
            " id_client," +
            "id_car," +
            "rental_start_date," +
            "rental_end_date," +
            "total_rental_cost," +
            "status_order_id " +
            "FROM order_rental";

    private static final String FIND_BY_ID_SQL =
            "SELECT order_rental_id," +
                    " id_client," +
                    "id_car," +
                    "rental_start_date," +
                    "rental_end_date," +
                    "total_rental_cost," +
                    "status_order_id " +
                    "FROM order_rental WHERE order_rental_id =?";

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
    public OrderRental create(OrderRental orderRental) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, orderRental.getClient().getClientId());
            preparedStatement.setLong(2, orderRental.getCar().getCarId());
            preparedStatement.setDate(3, Date.valueOf(orderRental.getRentalStartDate()));
            preparedStatement.setDate(4, Date.valueOf((orderRental.getRentalEndDate())));
            preparedStatement.setBigDecimal(5, orderRental.getTotalRentalCost());
            preparedStatement.setLong(6, orderRental.getStatusOrder().getStatusOrderId());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderRental.setOrderRentalId(generatedKeys.getLong(ORDER_RENTAL_ID));
            }
            return orderRental;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(OrderRental orderRental) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, orderRental.getClient().getClientId());
            preparedStatement.setLong(2, orderRental.getCar().getCarId());
            preparedStatement.setDate(3, Date.valueOf(orderRental.getRentalStartDate()));
            preparedStatement.setDate(4, Date.valueOf((orderRental.getRentalEndDate())));
            preparedStatement.setBigDecimal(5, orderRental.getTotalRentalCost());
            preparedStatement.setLong(6, orderRental.getStatusOrder().getStatusOrderId());
            preparedStatement.setLong(7, orderRental.getOrderRentalId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<OrderRental> findById(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<OrderRental> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            OrderRental orderRental = null;
            if (resultSet.next()) {
                orderRental = new OrderRental(
                        resultSet.getLong(ORDER_RENTAL_ID),
                        clientDaoImpl.findById(resultSet.getLong(ID_CLIENT),
                                resultSet.getStatement().getConnection()).orElse(null),
                        carDaoImpl.findById(resultSet.getLong(ID_CAR),
                                resultSet.getStatement().getConnection()).orElse(null),
                        resultSet.getDate(RENTAL_START_DATE).toLocalDate(),
                        resultSet.getDate(RENTAL_END_DATE).toLocalDate(),
                        resultSet.getBigDecimal(TOTAL_RENTAL_COST),
                        statusOrderDao.findById(resultSet.getLong(STATUS_ORDER_ID),
                                resultSet.getStatement().getConnection()).orElse(null));

            }
            return Optional.ofNullable(orderRental);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<OrderRental> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<OrderRental> orderRentals = new ArrayList<>();
            while (resultSet.next()) {
                orderRentals.add(buildOrder(resultSet));
            }
            return orderRentals;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private OrderRental buildOrder(ResultSet resultSet) throws SQLException {
        return new OrderRental(
                resultSet.getLong(ORDER_RENTAL_ID),
                clientDaoImpl.findById(resultSet.getLong(ID_CLIENT),
                        resultSet.getStatement().getConnection()).orElse(null),
                carDaoImpl.findById(resultSet.getLong(ID_CAR),
                        resultSet.getStatement().getConnection()).orElse(null),
                resultSet.getDate(RENTAL_START_DATE).toLocalDate(),
                resultSet.getDate(RENTAL_END_DATE).toLocalDate(),
                resultSet.getBigDecimal(TOTAL_RENTAL_COST),
                statusOrderDao.findById(resultSet.getLong(STATUS_ORDER_ID),
                resultSet.getStatement().getConnection()).orElse(null));

    }

    public static OrderDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderDaoImpl();
        }
        return INSTANCE;
    }
}
