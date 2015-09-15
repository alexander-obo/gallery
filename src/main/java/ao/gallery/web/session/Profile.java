package ao.gallery.web.session;

import ao.gallery.dao.Picture;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Profile {

    private List<Picture> pictures;
    private List<String> thumbnailsBase64;

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        prepareThumbnails();
    }

    public List<String> getThumbnails() {
        return thumbnailsBase64;
    }

    private void prepareThumbnails() {
        thumbnailsBase64 = new ArrayList<>();
        Base64.Encoder encoder = Base64.getEncoder();
        for (Picture picture : pictures) {
            byte[] thumbnailBytes = picture.getThumbnail();
            thumbnailsBase64.add(encoder.encodeToString(thumbnailBytes));
        }
    }

}
