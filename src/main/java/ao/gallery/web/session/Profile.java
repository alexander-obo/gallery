package ao.gallery.web.session;

import ao.gallery.dao.Picture;
import java.util.List;

public class Profile {

    private List<Picture> pictures;

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

}
