import java.util.Arrays;

public class Project10 {
    public static int select(int[] list, int left, int right, int n) {
        if (left == right)
            return left;

        while (true) {
            int pivotIndex = pivot(list, left, right);
            pivotIndex = partition(list, left, right, pivotIndex);

            if (n == pivotIndex)
                return n;
            else if (n < pivotIndex)
                right = pivotIndex - 1;
            else
                left = pivotIndex + 1;
        }
    }

    static int partition(int[] list, int left, int right, int pivotIndex) {
        int pivotValue = list[pivotIndex];

        list[pivotIndex] = list[right];
        list[right] = pivotValue;

        int storeIndex = left;

        for (int i = left; i <= right - 1; i++) {
            if (list[i] < pivotValue) {
                int temp = list[i];
                list[i] = list[storeIndex];
                list[storeIndex] = temp;
                storeIndex++;
            }
        }

        list[right] = list[storeIndex];
        list[storeIndex] = pivotValue;

        return storeIndex;
    }

    static int pivot(int[] list, int left, int right) {
        if (right - left < 5)
            return partition5(list, left, right);

        for (int i = left; i <= right; i+= 5) {
            int subRight = i + 4;
            if (subRight > right)
                subRight = right;

            int median5 = partition5(list, i, subRight);

            int temp = list[median5];
            int replace = left + (i - left) / 5;
            list[median5] = list[replace];
            list[replace] = temp;
        }

        // Values for return
        int returnRight = left + (int)(Math.ceil((right - left) / 5.0)) - 1;
        int n = left + (right - left) / 10;

        return select(list, left, returnRight, n);
    }

    static int partition5(int[] list, int left, int right) {
        // Partition 5 or less elements in specified range
        // Start range parameter is inclusive and right range parameter is 
        // exclusive, hence right + 1
        Arrays.sort(list, left, right + 1);

        return (left + right) / 2;
    }
}
