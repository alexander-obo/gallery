package ao.gallery.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySQLDAO implements DAO {

    private static final Logger LOG = LogManager.getLogger(MySQLDAO.class);
    private static MySQLDAO instance;

    private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static String dbHost = "localhost";
    private static String dbPort = "3306";
    private static String dbName = "gallery";
    private static String dbConnectionUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
    private static String dbUserName = "alexander";
    private static String dbUserPassword = "ao_password";
    private static final String INSERT_PICTURE_QUERY = "INSERT INTO users_pictures (picture_name, uploader_name, picture, thumbnail) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_PICTURES_QUERY = "SELECT * FROM users_pictures WHERE uploader_name = ?";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (user_email, user_name, user_password) VALUES (?, ?, ?)";
    private static final String INSERT_USER_ROLE_QUERY = "INSERT INTO user_roles (user_name, role_name) VALUES (?, ?)";
    private static final String SELECT_USERS_NAMES_QUERY = "SELECT user_name FROM users WHERE user_name LIKE ?";

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
            LOG.error(ex);
        }
        try {
            InitialContext initCtx = new InitialContext();
            Context defCtx = (Context) initCtx.lookup("java:comp/env");
            String dbConfigurationPath = (String) defCtx.lookup("dbConfigurationPath");
            FileInputStream fis = new FileInputStream(dbConfigurationPath);
            Properties properties = new Properties();
            properties.load(fis);
            dbHost = properties.getProperty("host");
            dbPort = properties.getProperty("port");
            dbName = properties.getProperty("db_name");
            dbUserName = properties.getProperty("user_name");
            dbUserPassword = properties.getProperty("user_password");
            dbConnectionUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            LOG.info("DB URL: {}", dbConnectionUrl);
        } catch (NamingException | IOException ex) {
            LOG.error(ex);
        }
    }

    @Override
    public void addPicture(Picture picture) throws DAOException {
        try (Connection connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbUserPassword);
                PreparedStatement statement = connection.prepareStatement(INSERT_PICTURE_QUERY)) {
            LOG.info("Adding picture \"{}\", uploader \"{}\"...", picture.getName(), picture.getOwnerName());
            statement.setString(1, picture.getName());
            statement.setString(2, picture.getOwnerName());
            statement.setBytes(3, picture.getContent());
            statement.setBytes(4, picture.getThumbnail());
            statement.execute();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Picture> getUserPictures(String userName) throws DAOException {
        List<Picture> pictures = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbUserPassword);
                PreparedStatement statement = connection.prepareStatement(SELECT_USER_PICTURES_QUERY)) {
            statement.setString(1, userName);
            LOG.info("Loading \"{}\" pictures...", userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int pictureId = resultSet.getInt(1);
                String pictureName = resultSet.getString(2);
                // TODO do not query picture uploader name, it is parameter
                String pictureUploaderName = resultSet.getString(3);
                byte[] pictureContent = resultSet.getBytes(4);
                byte[] thumbnail = resultSet.getBytes(5);
                Picture picture = new Picture(pictureId, pictureName, pictureUploaderName, pictureContent, thumbnail);
                pictures.add(picture);
            }
            LOG.info("Loaded {} pictures, uploader \"{}\"", pictures.size(), userName);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        return pictures;
    }

    @Override
    public void addUser(User user) throws DAOException {
        try (Connection connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbUserPassword)) {
            connection.setAutoCommit(false);
            try (PreparedStatement addUserStatement = connection.prepareStatement(INSERT_USER_QUERY);
                    PreparedStatement addUserRoleStatement = connection.prepareStatement(INSERT_USER_ROLE_QUERY)) {
                LOG.info("Adding user \"{}\"...", user.getLogin());
                addUserStatement.setString(1, user.getEmail());
                addUserStatement.setString(2, user.getLogin());
                addUserStatement.setString(3, user.getPassword());
                addUserStatement.execute();
                addUserRoleStatement.setString(1, user.getLogin());
                addUserRoleStatement.setString(2, user.getRole());
                addUserRoleStatement.execute();
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public List<String> getUsersNames(String name) throws DAOException {
        List<String> usersNames = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbUserPassword);
                PreparedStatement statement = connection.prepareStatement(SELECT_USERS_NAMES_QUERY)) {
            LOG.info("Loading users names which start with \"{}\"...", name);
            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usersNames.add(resultSet.getString(1));
            }
            LOG.info("Loaded {} users names", usersNames.size());
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        return usersNames;
    }

}
