import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by Jonathan Moyett
 */
public class Main {
    public static void main(String[] args) {
        int[] key1 = {9, 2, 3, 4, 5, 6, 7, 8, 1};
        int[] key2 = {1, 8, 3, 4, 5, 6, 7, 2, 9};
        int[] key3 = {1, 2, 7, 4, 5, 6, 3, 8, 9};
        int[] key4 = {1, 2, 3, 6, 5, 4, 7, 8, 9};
        int[] blockKey = {19, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 1};

        File file = new File("src/text.txt");
        ArrayList<Byte> byteList = new ArrayList<>();

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int result = inputStream.read();
            while (result != -1) {
                byteList.add((byte) result);
                result = inputStream.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get bits
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = byteList.get(i);
        BitSet bitSet = BitSet.valueOf(bytes);

        // get list of blocks
        ArrayList<int[][]> blockList = new ArrayList<>();
        for (int i = 0; i < bitSet.length() / 100; i++) {
            int[][] block = new int[10][10];
            for (int j = 0; j < 100; j++) {
                if (bitSet.get(i * 100 + j))
                    block[j / 10][j % 10] = 1;
                else
                    block[j / 10][j % 10] = 0;
            }
            blockList.add(block);
        }


        // apply transposition to subblocks
        ArrayList<int[][]> subblockList = new ArrayList<>();
        for (int[][] block : blockList) {
            for (int i = 0; i < 4; i++) {
                int[][] subblock = new int[5][5];
                int rowOffset = 0;
                int columnOffset = 0;
                switch (i) {
                    case 0: // top left block
                        rowOffset = 0;
                        columnOffset = 0;
                        break;
                    case 1: // top right block
                        rowOffset = 0;
                        columnOffset = 5;
                        break;
                    case 2: // bottom left block
                        rowOffset = 5;
                        columnOffset = 0;
                        break;
                    case 3: // bottom right block
                        rowOffset = 5;
                        columnOffset = 5;
                        break;
                }

                for (int j = 0; j < 25; j++) {
                    subblock[j / 5][j % 5] = block[j / 5 + rowOffset][j % 5 + columnOffset];
                }
                subblockList.add(subblock);
            }
        }

    }
}
