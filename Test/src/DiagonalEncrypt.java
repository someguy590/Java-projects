import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 */
public class DiagonalEncrypt {
    public static enum EncryptionMode {
        ECB, CBC, CTR
    }

    private int[][] keys = new int[4][9];
    private int[] blockKey = new int[19];
    private EncryptionMode encryptionMode = EncryptionMode.ECB;
    private int[][] previousBlock = IV;
    private static int CTRModeCount = 0;
    private static final int[][] IV = {
            {1, 0, 1, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 0, 1, 1, 0},
            {1, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 0, 1, 1, 0, 0},
            {1, 0, 1, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 1, 1, 0, 1, 1, 0},
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 1, 0, 0, 0},
            {1, 1, 0, 1, 1, 1, 0, 0, 0, 0}
    };



    public DiagonalEncrypt(int[][] keys, int[] blockKey) {
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < keys[i].length; j++)
                this.keys[i][j] = keys[i][j];
        }

        for (int i = 0; i < blockKey.length; i++)
            this.blockKey[i] = blockKey[i];
    }

    public File encrypt(File file) throws IOException {
        // find number of characters in file
        int fileLength = 0;
        BufferedReader r = new BufferedReader(new FileReader(file));
        while (r.read() > 0)
            fileLength++;
        r.close();

        // create output file
        File outputFile = new File("ciphertext.txt");
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");

        if (fileLength == 0)
            return outputFile;

        // pad file to ensure size is multiples of 100 bits
        if (fileLength % 25 != 0) {
            long charAddCount = 25 - (fileLength % 25);
            PrintWriter w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            for (int i = 0; i < charAddCount; i++)
                w.print(' ');
            w.close();
            fileLength += charAddCount;
        }

        // read file
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        ArrayList<Integer> leftOverBits = new ArrayList<>();
        for (int i1 = 0; i1 < (fileLength / 25) * 2; i1++) {
            // read in 12 characters at a time (about 1 block)
            ArrayList<Integer> byteList = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                int result = reader.read();
                if (result < 0) // no more bytes to read
                    break;
                byteList.add(result);
            }

            // fill in block
            int[][] block = new int[10][10];
            int blockIndex = 0;

            // fill in left over bits from previous blocks first
            if (!leftOverBits.isEmpty()) {
                for (int i = 0; i < leftOverBits.size(); i++, blockIndex++)
                    block[blockIndex / 10][blockIndex % 10] = leftOverBits.get(i);
                leftOverBits.clear();
            }

            // fill in read in bits from file
            for (int b : byteList) {
                String bitString = Integer.toBinaryString(b);
                while (bitString.length() < 8) {// ensure bit string of character is proper length of 8 bits
                    bitString = "0" + bitString;
                }

                for (int i = 0; i < bitString.length(); i++, blockIndex++) {
                    if (bitString.charAt(i) == '0')
                        block[blockIndex / 10][blockIndex % 10] = 0;
                    else
                        block[blockIndex / 10][blockIndex % 10] = 1;
                }
            }

            // get more characters if cannot fill block
            while (blockIndex < 100) {
                String s = "";
                int result = reader.read();
                if (result < 0) // no more bytes to read
                    s = Integer.toBinaryString((int) ' ');
                else // succesfully read next byte
                    s = Integer.toBinaryString(result);
                while (s.length() < 8) // ensure bit string of character is proper length of 8 bits
                    s = "0" + s;

                // fill in extra character
                for (int i = 0; i < s.length(); i++, blockIndex++) {
                    // if filled block in middle of reading bit string of character, save left over bits
                    if (blockIndex >= 100) {
                        for (; i < s.length(); i++) {
                            if (s.charAt(i) == '0')
                                leftOverBits.add(0);
                            else
                                leftOverBits.add(1);
                        }
                        break;
                    }

                    if (s.charAt(i) == '0')
                        block[blockIndex / 10][blockIndex % 10] = 0;
                    else
                        block[blockIndex / 10][blockIndex % 10] = 1;
                }
            }

            // encrypt block
            encryptBlock(block);

            // write block to file
            String string = "";
            for (int i = 0; i < block.length; i++) {
                // if already read first few bits, do not read again
                int startIndex = 0;
                if ((i == 0) && leftOverBits.isEmpty())
                    startIndex = 4;
                for (int j = startIndex; j < block.length; j++) {
                    string = string + block[i][j];
                    if (string.length() == 8) {
                        writer.print((char)Integer.parseInt(string, 2));
                        string = "";
                    }
                }
            }

            // use left over bits from block to write character
            if ((string.length() == 4) && !leftOverBits.isEmpty()) {
                for (int i : leftOverBits)
                    string = string + i;
                writer.print((char)Integer.parseInt(string, 2));
            }

        }

        previousBlock = IV;
        CTRModeCount = 0;
        reader.close();
        writer.close();

        return outputFile;
    }

    private void encryptBlock(int[][] block) {
        // if encrypt mode = CBC
        if (encryptionMode == EncryptionMode.CBC) {
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++)
                    block[i][j] = previousBlock[i][j] ^ block[i][j];
            }
        }

        // if encrypt mode = CTR
        int[][] plaintextBlock = new int[10][10];
        if (encryptionMode == EncryptionMode.CTR) {
            for (int i = 0; i < plaintextBlock.length; i++) {
                for (int j = 0; j < plaintextBlock.length; j++) {
                    plaintextBlock[i][j] = block[i][j];
                    block[i][j] = IV[i][j];
                }
            }

            // increment IV value before encrypting it
            String binaryString = "";
            for (int blockRow[] : block) {
                for (int i : blockRow) {
                    if (i == 0)
                        binaryString = binaryString + "0";
                    else
                        binaryString = binaryString + "1";
                }
            }

            BigInteger bigInteger = new BigInteger(binaryString, 2);
            bigInteger = bigInteger.add(BigInteger.valueOf(CTRModeCount++));

            binaryString = bigInteger.toString(2);
            if (binaryString.length() < 100) {
                while (binaryString.length() < 100)
                    binaryString = "0" + binaryString;
            }

            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++) {
                    if (binaryString.charAt(i * 10 + j) == '0')
                        block[i][j] = 0;
                    else
                        block[i][j] = 1;
                }
            }

        }

        // fill sublbocks
        for (int subblockNumber = 0; subblockNumber < 4; subblockNumber++) {
            // offsets which elements to read from block to subblocks
            int rowOffset = 0;
            int columnOffset = 0;
            switch (subblockNumber) {
                case 0:
                    rowOffset = 0;
                    columnOffset = 0;
                    break;
                case 1:
                    rowOffset = 0;
                    columnOffset = 5;
                    break;
                case 2:
                    rowOffset = 5;
                    columnOffset = 0;
                    break;
                case 3:
                    rowOffset = 5;
                    columnOffset = 5;
                    break;
            }

            // fill subblock
            int[][] subblock = new int[5][5];
            for (int i = 0; i < subblock.length; i++) {
                for (int j = 0; j < subblock.length; j++)
                    subblock[i][j] = block[i + rowOffset][j + columnOffset];
            }

            // apply key to subblock
            int[] key = keys[subblockNumber];
            for (int diagonalNumber = 0; diagonalNumber < 4; diagonalNumber++) { // just need to check first 4 values of key
                if (key[diagonalNumber] == (diagonalNumber + 1)) // skip transposition if no swap will occur
                    continue;

                // swap diagonals
                int diagonal1Row = 0;
                int diagonal1Column = diagonalNumber;
                int diagonal2Row = 4 - diagonalNumber;
                int diagonal2Column = 4;
                while (diagonal1Row <= 4 && diagonal1Column >= 0 && diagonal2Row <= 4 && diagonal2Column >= 0) {
                    int temp = subblock[diagonal1Row][diagonal1Column];
                    subblock[diagonal1Row][diagonal1Column] = subblock[diagonal2Row][diagonal2Column];
                    subblock[diagonal2Row][diagonal2Column] = temp;

                    diagonal1Row++;
                    diagonal1Column--;
                    diagonal2Row++;
                    diagonal2Column--;
                }
            }
        }

        // apply block key
        for (int diagonalNumber = 0; diagonalNumber < 8; diagonalNumber++) { // just need to check first 8 values of key
            if (blockKey[diagonalNumber] == (diagonalNumber + 1)) // skip transposition if no swap will occur
                continue;

            // swap diagonals
            int diagonal1Row = 0;
            int diagonal1Column = diagonalNumber;
            int diagonal2Row = 9 - diagonalNumber;
            int diagonal2Column = 9;
            while (diagonal1Row <= 9 && diagonal1Column >= 0 && diagonal2Row <= 9 && diagonal2Column >= 0) {
                int temp = block[diagonal1Row][diagonal1Column];
                block[diagonal1Row][diagonal1Column] = block[diagonal2Row][diagonal2Column];
                block[diagonal2Row][diagonal2Column] = temp;

                diagonal1Row++;
                diagonal1Column--;
                diagonal2Row++;
                diagonal2Column--;
            }
        }

        // if encrypt mode = CBC
        if (encryptionMode == EncryptionMode.CBC)
            previousBlock = block;

        // if encrypt mode = CTR
        if (encryptionMode == EncryptionMode.CTR) {
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++) {
                    block[i][j] = plaintextBlock[i][j] ^ block[i][j];
                }
            }
        }
    }

    public File decrypt(File file) throws IOException {
        // find number of characters in file
        int fileLength = 0;
        BufferedReader r = new BufferedReader(new FileReader(file));
        while (r.read() > 0)
            fileLength++;
        r.close();

        // create output file
        File outputFile = new File("plaintext.txt");
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");

        if (fileLength == 0)
            return outputFile;

        // pad file to ensure size is multiples of 100 bits
        if (fileLength % 25 != 0) {
            long charAddCount = 25 - (fileLength % 25);
            PrintWriter w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            for (int i = 0; i < charAddCount; i++)
                w.print(' ');
            w.close();
            fileLength += charAddCount;
        }

        // read file
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        ArrayList<Integer> leftOverBits = new ArrayList<>();
        for (int i1 = 0; i1 < (fileLength / 25) * 2; i1++) {
            // read in 12 characters at a time (about 1 block)
            ArrayList<Integer> byteList = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                int result = reader.read();
                if (result < 0) // no more bytes to read
                    break;
                byteList.add(result);
            }

            // fill in block
            int[][] block = new int[10][10];
            int blockIndex = 0;

            // fill in left over bits from previous blocks first
            if (!leftOverBits.isEmpty()) {
                for (int i = 0; i < leftOverBits.size(); i++, blockIndex++)
                    block[blockIndex / 10][blockIndex % 10] = leftOverBits.get(i);
                leftOverBits.clear();
            }

            // fill in read in bits from file
            for (int b : byteList) {
                String bitString = Integer.toBinaryString(b);
                while (bitString.length() < 8) // ensure bit string of character is proper length of 8 bits
                    bitString = "0" + bitString;

                for (int i = 0; i < bitString.length(); i++, blockIndex++) {
                    if (bitString.charAt(i) == '0')
                        block[blockIndex / 10][blockIndex % 10] = 0;
                    else
                        block[blockIndex / 10][blockIndex % 10] = 1;
                }
            }

            // get more characters if cannot fill block
            while (blockIndex < 100) {
                String s = "";
                int result = reader.read();
                if (result < 0) // no more bytes to read
                    s = Integer.toBinaryString((int) ' ');
                else // succesfully read next byte
                    s = Integer.toBinaryString(result);
                while (s.length() < 8) // ensure bit string of character is proper length of 8 bits
                    s = "0" + s;

                // fill in extra character
                for (int i = 0; i < s.length(); i++, blockIndex++) {
                    // if filled block in middle of reading bit string of character, save left over bits
                    if (blockIndex >= 100) {
                        for (; i < s.length(); i++) {
                            if (s.charAt(i) == '0')
                                leftOverBits.add(0);
                            else
                                leftOverBits.add(1);
                        }
                        break;
                    }

                    if (s.charAt(i) == '0')
                        block[blockIndex / 10][blockIndex % 10] = 0;
                    else
                        block[blockIndex / 10][blockIndex % 10] = 1;
                }
            }

            // decrypt block
            decryptBlock(block);

            // write block to file
            String string = "";
            for (int i = 0; i < block.length; i++) {
                // if already read first few bits, do not read again
                int startIndex = 0;
                if ((i == 0) && leftOverBits.isEmpty())
                    startIndex = 4;
                for (int j = startIndex; j < block.length; j++) {
                    string = string + block[i][j];
                    if (string.length() == 8) {
                        writer.print((char)Integer.parseInt(string, 2));
                        string = "";
                    }
                }
            }

            // use left over bits from block to write character
            if ((string.length() == 4) && !leftOverBits.isEmpty()) {
                for (int i : leftOverBits)
                    string = string + i;
                writer.print((char)Integer.parseInt(string, 2));
            }

        }

        previousBlock = IV;
        CTRModeCount = 0;
        reader.close();
        writer.close();

        return outputFile;
    }

    private void decryptBlock(int[][] block) {
        // if encrypt mode = CBC
        int[][] ciphertextBlockCBC = new int[10][10];
        if (encryptionMode == EncryptionMode.CBC) {
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++)
                    ciphertextBlockCBC[i][j] = block[i][j];
            }
        }

        // if encrypt mode = CTR
        int[][] ciphertextBlock = new int[10][10];
        if (encryptionMode == EncryptionMode.CTR) {
            for (int i = 0; i < ciphertextBlock.length; i++) {
                for (int j = 0; j < ciphertextBlock.length; j++) {
                    ciphertextBlock[i][j] = block[i][j];
                    block[i][j] = IV[i][j];
                }
            }

            // increment IV value before encrypting it
            String binaryString = "";
            for (int blockRow[] : block) {
                for (int i : blockRow) {
                    if (i == 0)
                        binaryString = binaryString + "0";
                    else
                        binaryString = binaryString + "1";
                }
            }

            BigInteger bigInteger = new BigInteger(binaryString, 2);
            bigInteger = bigInteger.add(BigInteger.valueOf(CTRModeCount++));

            binaryString = bigInteger.toString(2);
            if (binaryString.length() < 100) {
                while (binaryString.length() < 100)
                    binaryString = "0" + binaryString;
            }

            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++) {
                    if (binaryString.charAt(i * 10 + j) == '0')
                        block[i][j] = 0;
                    else
                        block[i][j] = 1;
                }
            }

        }

        // fill subblocks
        for (int subblockNumber = 0; subblockNumber < 4; subblockNumber++) {
            // offsets which elements to read from block to subblocks
            int rowOffset = 0;
            int columnOffset = 0;
            switch (subblockNumber) {
                case 0:
                    rowOffset = 0;
                    columnOffset = 0;
                    break;
                case 1:
                    rowOffset = 0;
                    columnOffset = 5;
                    break;
                case 2:
                    rowOffset = 5;
                    columnOffset = 0;
                    break;
                case 3:
                    rowOffset = 5;
                    columnOffset = 5;
                    break;
            }

            // fill subblock
            int[][] subblock = new int[5][5];
            for (int i = 0; i < subblock.length; i++) {
                for (int j = 0; j < subblock.length; j++)
                    subblock[i][j] = block[i + rowOffset][j + columnOffset];
            }

            // apply key to subblock
            int[] key = keys[subblockNumber];
            for (int diagonalNumber = 0; diagonalNumber < 4; diagonalNumber++) { // just need to check first 4 values of key
                if (key[diagonalNumber] == (diagonalNumber + 1)) // skip transposition if no swap will occur
                    continue;

                // swap diagonals
                int diagonal1Row = 0;
                int diagonal1Column = diagonalNumber;
                int diagonal2Row = 4 - diagonalNumber;
                int diagonal2Column = 4;
                while (diagonal1Row <= 4 && diagonal1Column >= 0 && diagonal2Row <= 4 && diagonal2Column >= 0) {
                    int temp = subblock[diagonal1Row][diagonal1Column];
                    subblock[diagonal1Row][diagonal1Column] = subblock[diagonal2Row][diagonal2Column];
                    subblock[diagonal2Row][diagonal2Column] = temp;

                    diagonal1Row++;
                    diagonal1Column--;
                    diagonal2Row++;
                    diagonal2Column--;
                }
            }
        }

        // apply block key
        for (int diagonalNumber = 0; diagonalNumber < 8; diagonalNumber++) { // just need to check first 8 values of key
            if (blockKey[diagonalNumber] == (diagonalNumber + 1)) // skip transposition if no swap will occur
                continue;

            // swap diagonals
            int diagonal1Row = 0;
            int diagonal1Column = diagonalNumber;
            int diagonal2Row = 9 - diagonalNumber;
            int diagonal2Column = 9;
            while (diagonal1Row <= 9 && diagonal1Column >= 0 && diagonal2Row <= 9 && diagonal2Column >= 0) {
                int temp = block[diagonal1Row][diagonal1Column];
                block[diagonal1Row][diagonal1Column] = block[diagonal2Row][diagonal2Column];
                block[diagonal2Row][diagonal2Column] = temp;

                diagonal1Row++;
                diagonal1Column--;
                diagonal2Row++;
                diagonal2Column--;
            }
        }

        // if encrypt mode = CBC
        if (encryptionMode == EncryptionMode.CBC) {
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++)
                    block[i][j] = previousBlock[i][j] ^ block[i][j];
            }
            previousBlock = ciphertextBlockCBC;
        }

        // if encrypt mode = CTR
        if (encryptionMode == EncryptionMode.CTR) {
            for (int i = 0; i < block.length; i++) {
                for (int j = 0; j < block.length; j++) {
                    block[i][j] = ciphertextBlock[i][j] ^ block[i][j];
                }
            }
        }
    }

    public void setKeys(int[][] keys) {
        for (int i = 0; i < this.keys.length; i++) {
            for (int j = 0; j < this.keys[i].length; j++) {
                this.keys[i][j] = keys[i][j];
            }
        }
    }

    public void setBlockKey(int[] blockKey) {
        for (int i = 0; i < this.blockKey.length; i++)
            this.blockKey[i] = blockKey[i];
    }

    public void setEncryptionMode(EncryptionMode encryptionMode) {
        this.encryptionMode = encryptionMode;
    }
}
