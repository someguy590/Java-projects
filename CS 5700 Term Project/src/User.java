import javafx.scene.image.*;

/**
 * Class to represent a user in the system
 * Communicates images to others by use of a shared buffer
 */
public class User {
    public static byte[] buffer;
    private int id;
    private byte[] localBuffer;
    private Image publicImage;
    private Image secretImage;

    public User(int id) {
        this.id = id;
        secretImage = new Image("image/pepsi.jpg");

        publicImage = new Image("image/coke.png");
        PixelReader reader = publicImage.getPixelReader();

        int width = (int) publicImage.getWidth();
        int height = (int) publicImage.getHeight();

        // Read publicImage pixels (4 bytes per pixel)
        localBuffer = new byte[width * height * 4];
        reader.getPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), localBuffer, 0, width * 4);
    }

    public void writeToBuffer(byte[] input) {
        localBuffer = input;
        buffer = new byte[localBuffer.length];
        System.arraycopy(localBuffer, 0, buffer, 0, localBuffer.length);
        updateImage();
    }

    public void readFromBuffer() {
        localBuffer = new byte[buffer.length];
        System.arraycopy(buffer, 0, localBuffer, 0, buffer.length);
        updateImage();
    }

    public void encryptDES(int mode) throws Exception {
        // Encrypt publicImage pixels
        DES des;
        if (mode == DES.ECB_MODE)
            des = new DES(DES.ECB_MODE);
        else
            des = new DES(DES.CBC_MODE);
        writeToBuffer(des.encrypt(localBuffer));
    }

    public void decryptDES(int mode) throws Exception {
        // Decrypt publicImage pixels
        DES des;
        if (mode == DES.ECB_MODE)
            des = new DES(DES.ECB_MODE);
        else
            des = new DES(DES.CBC_MODE);
        writeToBuffer(des.decrypt(localBuffer));
    }

    public void encryptVEA() {
        VEA v = new VEA();
        writeToBuffer(v.encrypt(localBuffer));
    }

    public void decryptVEA() {
        VEA v = new VEA();
        writeToBuffer(v.decrypt(localBuffer));
    }

    private void updateImage() {
        int width = (int)publicImage.getWidth();
        int height = (int)publicImage.getHeight();

        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();

        writer.setPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), localBuffer, 0, width * 4);

        publicImage = image;
    }

    public Image getPublicImage() {
        return publicImage;
    }

    public Image getSecretImage() {
        return secretImage;
    }

}
