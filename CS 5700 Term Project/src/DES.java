import com.sun.javafx.image.BytePixelSetter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;

/**
 * Class for DES encryption and decryption in ECB and CBC mode
 */
public class DES {
    public static final int ECB_MODE = 0;
    public static final int CBC_MODE = 1;
    private Cipher cipher;
    private SecretKey key;
    private IvParameterSpec iv;
    private int mode;

    public DES(int mode) throws Exception{
        // Initialize Cipher object and KeyGenerator
        this.mode = mode;
        if (mode == ECB_MODE)
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        else
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecureRandom random = new SecureRandom(new byte[]{0});
        keyGenerator.init(random);

        // Generate IV and key
        key = keyGenerator.generateKey();
        byte[] byteIV = new byte[8];
        random.nextBytes(byteIV);
        iv = new IvParameterSpec(byteIV);
    }

    public byte[] encrypt(byte[] input) throws Exception{
        if (mode == ECB_MODE)
            cipher.init(Cipher.ENCRYPT_MODE, key);
        else
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        return cipher.doFinal(input);
    }

    public byte[] decrypt(byte[] input) throws Exception{
        if (mode == ECB_MODE)
            cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
        else
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

        return cipher.doFinal(input);
    }
}