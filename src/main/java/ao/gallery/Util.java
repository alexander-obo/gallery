package ao.gallery;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Util {

    public static byte[] getPictureThumbnail(byte[] pictureBytes) throws IOException {
        byte[] thumbnailBytes = null;
        InputStream in = new ByteArrayInputStream(pictureBytes);
        BufferedImage bufferedThumbnail = ImageIO.read(in);
        int type = bufferedThumbnail.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedThumbnail.getType();
        bufferedThumbnail = resizeImage(bufferedThumbnail, type, 100, 100);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedThumbnail, "jpg", baos);
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
}
