import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by someguy590 on 11/15/2016.
 */
public class Main {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        printArray(array1);
        System.out.println(Arrays.toString(array1));
    }

    public static void printArray(int[] array) {
        array[0] = -32;
        System.out.println(Arrays.toString(array));
    }
}
