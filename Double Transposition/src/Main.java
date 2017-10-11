import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jonathan Moyett on 11/15/2016.
 */
public class Main {

    // some words founded by viewing permutated output stored here to look for
    public static String[] foundWords = {"WHICH","EACH", "MAKES", "HARD", "SERIES"};
    // closest match so far
    // E PERIENCEMAKES US BIGGER EVENSERIES OF METIMES ITLIFE IS A S EACH ONE THOUGH SO OF WHICH  IS HARDTO
    // Plaintext:
    // Life is a series of experiences each one of which makes us bigger even though sometimes it is hard to

    public static void main(String[] args) {
        String cipherText = "XENEVIGREGIEEENEXRCPSXXIOSEEFRGHOOXXTUSHHIXXCXOWHFESTIXMEMITISXEXLIXAFHXEAOSXCNEXUBESMASXKAROXDXIHTS";

        // initialize order of columns and rows
        ArrayList<Integer> columnOrder = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            columnOrder.add(i);
        ArrayList<Integer> rowOrder = new ArrayList<>(columnOrder);

        // load ciphertext into matrix
        char[][] array = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = cipherText.charAt(i * 10 + j);
            }
        }

        while (true) {
            // create a random permutation
            Collections.shuffle(columnOrder);
            Collections.shuffle(rowOrder);

            // permutate matrix
            char[][] permutatedArray = permutateArray(array, columnOrder, rowOrder);
            String crackAttempt = arrayToString(permutatedArray);

            // continue to next permutation if permuated output has no previously founded words
            if (!hasCommonWord(crackAttempt))
                continue;

            // print output with actual words in text
            // printArray(permutatedArray);
            System.out.println(crackAttempt);
        }

    }

    public static char[][] permutateArray(char[][] array, List<Integer> columnOrder, List<Integer> rowOrder) {
        char[][] columnPermutedArray = new char[10][10];

        // arrange columns
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                columnPermutedArray[i][j] = array[i][columnOrder.get(j)];
            }
        }

        // arrange rows
        char[][] permuatedArray = new char[10][10];
        for (int i = 0; i < 10; i++)
            permuatedArray[i] = columnPermutedArray[rowOrder.get(i)];

        return permuatedArray;
    }

    public static void printArray(char[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char ch = array[i][j];
                if (ch == 'X')
                    ch = ' ';
                System.out.print(ch);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String arrayToString(char[][] array) {
        String string = "";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char ch = array[i][j];
                if (ch == 'X')
                    ch = ' ';
                string += ch;
            }
        }

        return string;
    }

    public static boolean hasCommonWord(String phrase) {
        for (String word : foundWords) {
            if (!phrase.contains(word))
                return false;
        }
        return true;
    }
}
