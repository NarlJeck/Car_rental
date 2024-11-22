package dao.impl.clientImpl;

import dao.clientDao.RoleDao;
import entity.client.Role;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {
    private static final String ROLE_Id = "role_id";
    private static final String ROLE = "role";

    private static RoleDaoImpl INSTANCE;

    private RoleDaoImpl() {

    }

    private static final String DELETE_SQL = "DELETE FROM role WHERE role_id = ?";

    private static final String CREATE_SQL = "INSERT INTO role(" +
            "role) " +
            "VALUES (?)";

    private static final String UPDATE_SQL = "UPDATE role " +
            "SET role = ? " +
            "WHERE role_id = ?";

    private static final String FIND_ALL_SQL = "SELECT role_id," +
            " role.role " +
            "FROM role";

    private static final String FIND_BY_ID_SQL =
            "SELECT role_id," +
                    " role.role " +
                    "FROM role WHERE role_id =?";

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Exception Role - method(delete)", e);
        }
    }

    @Override
    public Role create(Role role) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getRole());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                role.setRoleId(generatedKeys.getLong(ROLE_Id));
            }
            return role;

        } catch (SQLException e) {
            throw new DaoException("Exception Role - method(create)", e);
        }
    }

    @Override
    public void update(Role role) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, role.getRole());
            preparedStatement.setLong(2, role.getRoleId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Exception Role - method(update)", e);
        }
    }

    @Override
    public Optional<Role> findById(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException("Exception Role - method(findById)", e);
        }
    }

    public Optional<Role> findById(Long id, Connection connection) {
        try (var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            ResultSet resultSet = prepareStatement.executeQuery();
            Role role = null;
            if (resultSet.next()) {
                role = new Role(
                        resultSet.getLong(ROLE_Id),
                        resultSet.getString(ROLE)
                );
            }
            return Optional.ofNullable(role);
        } catch (SQLException e) {
            throw new DaoException("Exception Role - method(findById), Connection", e);
        }

    }

    private Role buildRole(ResultSet resultSet) throws SQLException {
        return new Role(
                resultSet.getLong(ROLE_Id),
                resultSet.getString(ROLE));
    }

    @Override
    public List<Role> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();

            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(buildRole(resultSet));
            }
            return roles;
        } catch (SQLException e) {
            throw new DaoException("Exception Role - method(findAll)", e);
        }
    }

    public static RoleDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleDaoImpl();
        }

        return INSTANCE;
    }

}
