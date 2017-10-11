import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;

/**
 * Computer Security Home Work 3
 * Christopher West
 * Dante Broadnax
 * Jonathan Moyett
 * 3/7/2016
 */
public class HW3 {

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     */
    // Max block length to measure (in multiples of 8 bytes)
    final static int MAX_BLOCK_LENGTH = 1000;
    public static void main(String[] args) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, IOException, InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        
        // Create cipher objects and associated keys
        // Cipher objects account for algorithm type, key size, IV size, etc.
        
        // DES algorithm
        Cipher cipherDES = Cipher.getInstance("DES/CBC/PKCS5Padding");
        KeyGenerator keyGeneratorDES = KeyGenerator.getInstance("DES");
        keyGeneratorDES.init(new SecureRandom());
        SecretKey keyDES = keyGeneratorDES.generateKey();
        
        // TDES Option 1 algorithm (3 independent keys)
        Cipher cipherTDESOption1 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        KeyGenerator keyGeneratorTDESOption1 = KeyGenerator.getInstance("DESede");
        keyGeneratorTDESOption1.init(168, new SecureRandom());
        SecretKey keyTDESOption1 = keyGeneratorTDESOption1.generateKey();
        
        // TDES Option 2 algorithm (K1, K2 independent keys, K3 = K1)
        Cipher cipherTDESOption2 = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        KeyGenerator keyGeneratorTDESOption2 = KeyGenerator.getInstance("DESede");
        keyGeneratorTDESOption2.init(112, new SecureRandom());
        SecretKey keyTDESOption2 = keyGeneratorTDESOption2.generateKey();
        
        // Generate IV all encryption schemes use
        Random random = new SecureRandom();
        byte[] byteIV = new byte[8];
        random.nextBytes(byteIV);
        IvParameterSpec iv = new IvParameterSpec(byteIV);
        
        // Hold message length vs throughput data for all algorithms
        int[] messageLength = new int[MAX_BLOCK_LENGTH];
        double[] encryptionThroughput = new double[MAX_BLOCK_LENGTH];
        double[] decryptionThroughput = new double[MAX_BLOCK_LENGTH];
        
        // DES data
        timeAlgorithm(cipherDES, keyDES, iv, messageLength,
                encryptionThroughput, decryptionThroughput);
        
        printThroughputToFile("DES", messageLength, encryptionThroughput,
                decryptionThroughput);
        
        // TDES Option 1 data
        timeAlgorithm(cipherTDESOption1, keyTDESOption1, iv, 
                messageLength, encryptionThroughput, decryptionThroughput);
        
        printThroughputToFile("TDESOption1", messageLength, 
                encryptionThroughput, decryptionThroughput);
        
        // TDES  Option 2 data
        timeAlgorithm(cipherTDESOption2, keyTDESOption2, iv,
                messageLength, encryptionThroughput,decryptionThroughput);
        
        printThroughputToFile("TDESOption2", messageLength, 
                encryptionThroughput, decryptionThroughput);
    }
    
    /**
     * 
     * @param cipher
     * @param key
     * @param iv
     * @param messageLengths
     * @param encryptThroughput
     * @param decryptThroughput
     */
    public static void timeAlgorithm(
            Cipher cipher, SecretKey key, IvParameterSpec iv, 
            int[] messageLengths, double[] encryptThroughput, 
            double[] decryptThroughput) 
            throws NoSuchAlgorithmException, InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException, 
            InvalidAlgorithmParameterException, NoSuchPaddingException {
        
        Random r = new Random(0); // Ensure all algorithms use same strings
        for (int i = 0; i < MAX_BLOCK_LENGTH; i++) {
            messageLengths[i] = i * 64 + 64; // min block length is 64 bits
            byte[] byteMessage = new byte[i * 8];
            r.nextBytes(byteMessage);
            
            byte[] byteCiphertext;
            
            // Time encryption
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            long startTime = System.nanoTime();
            byteCiphertext = cipher.doFinal(byteMessage);
            long endTime = System.nanoTime() - startTime;
            encryptThroughput[i] = byteCiphertext.length / ((double)endTime / 1E9);
            
            // Time decryption
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            startTime = System.nanoTime();
            cipher.doFinal(byteCiphertext);
            endTime = System.nanoTime() - startTime;
            decryptThroughput[i] = byteCiphertext.length / ((double)endTime / 1E9);
        }
    }
    
    public static void printThroughputToFile(String algorithm, int[] messageLength, double[] encryptionThroughput, double[] decryptionThroughput) throws FileNotFoundException {
        PrintWriter outputEncryption = new PrintWriter(algorithm + "EncryptionThroughput.txt");
        PrintWriter outputDecryption = new PrintWriter(algorithm + "DecryptionThroughput.txt");
        
        outputEncryption.println("Message Length(bits), Throughput (Octet/sec)");
        outputDecryption.println("Message Length(bits), Throughput (Octet/sec)");
        
        for (int i = 0; i < messageLength.length; i++) {
            outputEncryption.println(messageLength[i] + ", " + encryptionThroughput[i]);
            outputDecryption.println(messageLength[i] + ", " + decryptionThroughput[i]);
        }
        
        outputEncryption.close();
        outputDecryption.close();
    }
}
