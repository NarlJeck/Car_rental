import dao.impl.clientImpl.ClientDaoImpl;

import java.sql.SQLException;


public class JDBCRunner {

    public static void main(String[] args) throws SQLException {



        System.out.println(ClientDaoImpl.getInstance().findById(1L));

    }


}
