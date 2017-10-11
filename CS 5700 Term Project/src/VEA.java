import javafx.scene.image.Image;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for VEA encryption and decryption
 */
public class VEA {
    private static final byte[] PADDING_BYTES =  {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};

    public byte[] encrypt(byte[] x) {
        return encryptHelper(x, true);
    }

    public byte[] decrypt(byte[] x) {
        return encryptHelper(x, false);
    }

    private byte[] encryptHelper(byte[] x, boolean isEncryption) {
        // Pad input xPadded to be 64 bit blocks if encrypting
        byte[] xPadded = x;
        if (isEncryption) {
            int paddingLength = 8 - (x.length % 8);

            xPadded = new byte[x.length + paddingLength];
            System.arraycopy(x, 0, xPadded, 0, x.length);

            byte paddingByte = PADDING_BYTES[paddingLength - 1];

            for (int i = x.length; i < xPadded.length; i++)
                xPadded[i] = paddingByte;
        }

        // Round function applied on each block
        byte[] byteLeft = new byte[4];
        byte[] byteRight = new byte[4];
        int xL = 0;
        int xR = 0;
        byte[] xPaddedCipher = new byte[xPadded.length];

        // Iterate through blocks of input
        for (int i = 0; i < xPadded.length / 8; i++) {
            // Divide next xPadded block into two 32-bit halves
            System.arraycopy(xPadded, i * 8, byteLeft, 0, byteLeft.length);
            System.arraycopy(xPadded, i * 8 + byteLeft.length, byteRight, 0, byteRight.length);

            // Convert left/right into integers for easier handling
            xL = ByteBuffer.wrap(byteLeft).getInt();
            xR = ByteBuffer.wrap(byteRight).getInt();

            // Apply rounds
            if (isEncryption) {
                for (int j = 0; j < 16; j++) {
                    xL = xL ^ Boxes.pArray[j];
                    xR = fFunction(xL) ^ xR;
                    int temp = xL;
                    xL = xR;
                    xR = temp;
                }
            }
            else {
                for (int j = 17; j >= 2; j--) {
                    xL = xL ^ Boxes.pArray[j];
                    xR = fFunction(xL) ^ xR;
                    int temp = xL;
                    xL = xR;
                    xR = temp;
                }
            }

            // Undo last swap from loop
            int temp = xL;
            xL = xR;
            xR = temp;

            // Final XORS
            if (isEncryption) {
                xR = xR ^ Boxes.pArray[16];
                xL = xL ^ Boxes.pArray[17];
            }
            else {
                xR = xR ^ Boxes.pArray[1];
                xL = xL ^ Boxes.pArray[0];
            }

            // Convert left/right back to byte[]
            byteLeft = ByteBuffer.allocate(4).putInt(xL).array();
            byteRight = ByteBuffer.allocate(4).putInt(xR).array();

            // Write ciphered blocks
            System.arraycopy(byteLeft, 0, xPaddedCipher, i * 8, byteLeft.length);
            System.arraycopy(byteRight, 0, xPaddedCipher, i * 8 + byteLeft.length, byteRight.length);
        }

        if (isEncryption)
            return xPaddedCipher;

        // Strip off padding since decrypting
        int padAmount = xPaddedCipher[xPaddedCipher.length - 1];

        byte[] xUnpadded = new byte[xPaddedCipher.length - padAmount];
        System.arraycopy(xPaddedCipher, 0, xUnpadded, 0, xPaddedCipher.length - padAmount);

        return xUnpadded;

    }

    private int fFunction(int xL) {
        byte[] left = ByteBuffer.allocate(4).putInt(xL).array();
        int n = (int)(Math.pow(2, 32));

        // Value of substitution boxes with left[i] input
        // left[i] & 0xFF ensures unsigned values (no negatives)
        long s1a = Boxes.sBox1[left[0] & 0xFF];
        long s2b = Boxes.sBox2[left[1] & 0xFF];
        long s3c = Boxes.sBox3[left[2] & 0xFF];
        long s4d = Boxes.sBox4[left[3] & 0xFF];

        return (int)(((((s1a + s2b) % n) ^ s3c) + s4d) % n);
    }

}
