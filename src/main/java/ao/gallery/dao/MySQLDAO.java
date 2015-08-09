package ao.gallery.dao;

import java.io.File;

public class MySQLDAO implements DAO {

    private static MySQLDAO instance;

    private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "gallery";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String USER_NAME = "alexander";
    private static final String USER_PASSWORD = "ao_password";

    public static MySQLDAO getInstance() {
        if (instance == null) {
            instance = new MySQLDAO();
        }
        return instance;
    }

    private MySQLDAO() {
        try {
            Class.forName(JDBC_DRIVER_CLASS);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void addPicture(File picture) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
