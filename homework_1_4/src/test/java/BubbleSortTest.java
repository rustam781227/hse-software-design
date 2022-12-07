package test.java;

import main.java.BubbleSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
    @Test
    void unsortedArrayMustBecomeSorted() {
        BubbleSort bubble = new BubbleSort();
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, bubble.bubbleSort(new int[]{5, 4, 3, 2, 1}));
    }
}