package ao.gallery.dao;

import java.util.List;

public interface DAO {

    void addPicture(Picture picture) throws DAOException;

    List<Picture> getUserPictures(String userName) throws DAOException;

    void addUser(User user) throws DAOException;

}
