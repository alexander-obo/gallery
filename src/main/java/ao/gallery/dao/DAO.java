package ao.gallery.dao;

import java.io.InputStream;

public interface DAO {

    void addPicture(String pictureName, String uploaderName, InputStream picture, InputStream picturePreview) throws AddPictureException;

}
