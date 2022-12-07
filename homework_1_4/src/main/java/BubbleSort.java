package main.java;

public class BubbleSort {

    public static int[] bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; ++i) {
            int index = 0;
            while (index < arr.length - 1 && arr[index] > arr[index + 1]) {
                temp = arr[index];
                arr[index] = arr[index + 1];
                arr[index + 1] = temp;
                ++index;
            }
        }
        return arr;
    }
}
