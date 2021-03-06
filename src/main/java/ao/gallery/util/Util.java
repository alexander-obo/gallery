package ao.gallery.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

public class Util {

    private static final int MAX_THUMBNAIL_SIDE_LENGTH = 100;

    public static byte[] getPictureThumbnail(byte[] pictureBytes) throws IOException {
        InputStream in = new ByteArrayInputStream(pictureBytes);
        BufferedImage picture = ImageIO.read(in);
        int pictureWidth = picture.getWidth();
        int pictureHeight = picture.getHeight();
        double compressionRate = calculateCompressionRate(pictureWidth, pictureHeight, MAX_THUMBNAIL_SIDE_LENGTH);
        int thumbnailWidth = (int) (pictureWidth * compressionRate);
        int thumbnailHeight = (int) (pictureHeight * compressionRate);
        int type = picture.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : picture.getType();
        BufferedImage thumbnail = resizeImage(picture, type, thumbnailWidth, thumbnailHeight);
        byte[] thumbnailBytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(thumbnail, "jpg", baos);
            baos.flush();
            thumbnailBytes = baos.toByteArray();
        }
        return thumbnailBytes;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    private static double calculateCompressionRate(int width, int height, int maxSize) {
        int maximum = width > height ? width : height;
        return 1.0 * maxSize / maximum;
    }

    public static List<ao.gallery.web.Picture> convertPicturesDataToView(List<ao.gallery.dao.Picture> pictures) {
        List<ao.gallery.web.Picture> views = new ArrayList<>(pictures.size());
        Base64.Encoder encoder = Base64.getEncoder();
        for (ao.gallery.dao.Picture picture : pictures) {
            ao.gallery.web.Picture view = new ao.gallery.web.Picture();
            view.setId(picture.getId());
            view.setName(picture.getName());
            view.setThumbnail(encoder.encodeToString(picture.getThumbnail()));
            view.setContent(encoder.encodeToString(picture.getContent()));
            views.add(view);
        }
        return views;
    }
}
