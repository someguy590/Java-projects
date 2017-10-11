import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/**
 * Created by Jonathan Moyett
 */
public class Main {
    private static final int[][] IV = {

    };
    public static void main(String[] args) throws IOException {
//        int[][] keys = {
//                {1, 2, 3, 4, 5, 6, 7, 8, 9},
//                {1, 2, 3, 4, 5, 6, 7, 8, 9},
//                {1, 2, 3, 4, 5, 6, 7, 8, 9},
//                {1, 2, 3, 4, 5, 6, 7, 8, 9}
//        };

        int[][] keys = {
                {1, 8, 3, 6, 5, 4, 7, 2, 9},
                {9, 2, 7, 4, 5, 6, 3, 8, 1},
                {9, 8, 3, 4, 5, 6, 7, 2, 1},
                {1, 8, 7, 6, 5, 4, 3, 2, 9}
        };

//        int[] blockKey = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        int[] blockKey = {19, 2, 17, 4, 15, 6, 7, 12, 9, 10, 11, 8, 13, 14, 5, 16, 3, 18, 1};

        File file1 = new File("text.txt");
        File file2 = new File("ciphertext.txt");

        DiagonalEncrypt diagonalEncrypt = new DiagonalEncrypt(keys, blockKey);
        diagonalEncrypt.setEncryptionMode(DiagonalEncrypt.EncryptionMode.CTR);

        diagonalEncrypt.encrypt(file1);
        diagonalEncrypt.decrypt(file2);




    }
}
