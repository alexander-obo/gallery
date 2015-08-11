package ao.gallery.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDAO implements DAO {

    private static MySQLDAO instance;

    private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "gallery";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String USER_NAME = "alexander";
    private static final String USER_PASSWORD = "ao_password";
    private static final String INSERT_PICTURE_QUERY = "INSERT INTO users_pictures (picture_name, uploader_name, picture, thumbnail) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_PICTURES_QUERY = "SELECT * FROM users_pictures WHERE uploader_name = ?";

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
    public void addPicture(Picture picture) throws DAOException {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER_NAME, USER_PASSWORD);
                PreparedStatement statement = connection.prepareStatement(INSERT_PICTURE_QUERY)) {
            statement.setString(1, picture.getName());
            statement.setString(2, picture.getOwnerName());
            statement.setBinaryStream(3, picture.getContent());
            statement.setBinaryStream(4, picture.getThumbnail());
            statement.execute();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Picture> getUserPictures(String userName) throws DAOException {
        List<Picture> pictures = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER_NAME, USER_PASSWORD);
                PreparedStatement statement = connection.prepareStatement(SELECT_USER_PICTURES_QUERY)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int pictureId = resultSet.getInt(1);
                String pictureName = resultSet.getString(2);
                // TODO do not query picture uploader name, it is parameter
                String pictureUploaderName = resultSet.getString(3);
                InputStream pictureContent = resultSet.getBinaryStream(4);
                InputStream thumbnail = resultSet.getBinaryStream(5);
                Picture picture = new Picture(pictureId, pictureName, pictureUploaderName, pictureContent, thumbnail);
                pictures.add(picture);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        return pictures;
    }

}
