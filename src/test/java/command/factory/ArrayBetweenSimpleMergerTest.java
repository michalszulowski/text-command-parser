package command.factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBetweenSimpleMergerTest {
    private static ArrayBetweenSimpleMerger merger;
    private static int[] arr1;
    private static int[] arr2;
    private static int[] result;

    @BeforeAll
    public static void initializeMerger() {
        merger = new ArrayBetweenSimpleMerger();
    }

    @Test
    public void basicTest() {
        givenArrays(new int[]{10, 20}, new int[]{5, 9, 15, 21, 25, 30});
        whenMerging();
        thenResultShouldBe(new int[]{5, 9, 10, 20, 21, 25, 30});
    }

    @Test
    public void testMajorPairAtTheEnd() {
        givenArrays(new int[]{10, 20, 50, 60}, new int[]{5, 9, 15, 20, 21, 25, 30, 55});
        whenMerging();
        thenResultShouldBe(new int[]{5, 9, 10, 20, 21, 25, 30, 50, 60});
    }

    @Test
    public void duplicatesTest() {
        givenArrays(new int[]{10, 20}, new int[]{5, 9, 15, 20, 21, 25, 30});
        whenMerging();
        thenResultShouldBe(new int[]{5, 9, 10, 20, 21, 25, 30});
    }

    @Test
    public void fewMajorPairsTest() {
        givenArrays(new int[]{10, 20, 26, 40, 100, 150}, new int[]{5, 9, 15, 21, 25, 30, 35, 48, 52, 75, 110, 160});
        whenMerging();
        thenResultShouldBe(new int[]{5, 9, 10, 20, 21, 25, 26, 40, 48, 52, 75, 100, 150, 160});
    }

    @Test
    public void emptyMajorTest() {
        givenArrays(new int[]{}, new int[]{5, 9, 15, 20, 40});
        whenMerging();
        thenResultShouldBe(new int[]{5, 9, 15, 20, 40});
    }


    private void givenArrays(int[] a1, int[] a2) {
        arr1 = a1;
        arr2 = a2;
    }

    private void whenMerging() {
        result = merger.merge(arr1, arr2);
    }

    private void thenResultShouldBe(int[] expected) {
        assertArrayEquals(expected, result);
    }

    private int[] createArray(int... elems) {
        return elems;
    }
}