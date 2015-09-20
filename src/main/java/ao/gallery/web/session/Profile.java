package ao.gallery.web.session;

import ao.gallery.web.Picture;
import java.util.List;

public class Profile {

    private List<Picture> pictures;

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

}
