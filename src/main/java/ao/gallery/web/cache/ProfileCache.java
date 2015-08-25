package ao.gallery.web.cache;

import ao.gallery.dao.Picture;
import java.util.List;

public class ProfileCache {

    private List<Picture> pictures;

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

}
