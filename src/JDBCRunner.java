import dao.CarDao;

import java.sql.SQLException;


public class JDBCRunner {

    public static void main(String[] args) throws SQLException {
////        Class<Driver> driverClass = Driver.class;
//        try (var open = ConnectionManager.get()) {
//            System.out.println(open.getMetaData());
//        }finally {
//            ConnectionManager.closePool();
//        }
        var carDao = new CarDao().getInstance();
        boolean delete = carDao.delete(1L);
        System.out.println(delete);


    }


}
