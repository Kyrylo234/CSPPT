package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.Exceptions.FileDoesNotExistException;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.FileInterface;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.FileSystem.impl.FileInterfaceImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class Image extends DisplayComponentBase {

    private String imageBase64;
    private String fileType;

    /**
     * Constructor
     * Create a new empty object
     */
    public Image() {
        super("image");
    }

    public Image(int x, int y, int layer) {
        super(x, y, layer, "image", 100, 100);
    }

    public Image(int x, int y, int layer, int width, int height) {
        super(x, y, layer, "image", width, height);
    }

    public Image(int x, int y, int layer, int width, int height, String image, String fileType) {
        super(x, y, layer, "image", width, height);
        this.imageBase64 = image;
        this.fileType = fileType;
    }

    public java.awt.Image getImage() {
        byte[] imageBytes = Base64.getDecoder().decode(this.imageBase64);
        try {
            return ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Set the image of the object
     * IMPORTANT: You need to save the fileType before calling this method
     * @param image
     */
    public void setImage(java.awt.Image image) {
        convertImageToBase64(image, this.fileType);
    }

    public String getImageBase64() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), imageBase64);
    }

    public void setImageBase64(String image) {
        this.imageBase64 = image;
    }

    public String getFileType() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), fileType);

    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Convert an image to base64 string and save it to the object
     * @param image
     * @param type
     */
    public void convertImageToBase64(java.awt.Image image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            BufferedImage bImage = toBufferedImage(image);
            ImageIO.write(bImage, type, bos);
            byte[] imageBytes = bos.toByteArray();

            Base64.Encoder encoder = Base64.getEncoder();
            imageString = encoder.encodeToString(imageBytes);
            this.imageBase64 = imageString;

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage toBufferedImage(java.awt.Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        bimage.getGraphics().drawImage(img, 0, 0, null);
        bimage.getGraphics().dispose();

        return bimage;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Image image = (Image) object;
        return Objects.equals(getImageBase64(), image.getImageBase64()) && Objects.equals(getFileType(), image.getFileType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getImageBase64(), getFileType());
    }
}
