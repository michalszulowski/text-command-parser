package command.factory;

import java.util.ArrayList;
import java.util.List;

public class ArrayBetweenSimpleMerger implements ArrayBetweenMerger {
    private int[] majorArr;
    private int[] secondaryArr;
    private int majIter;
    private int secIter;
    private List<Integer> result;

    @Override
    public int[] merge(int[] major, int[] secondary) {
        majorArr = major;
        secondaryArr = secondary;
        majIter = 0;
        secIter = 0;
        result = new ArrayList<>();
        iterateOverArraysUntilMajorHasElements();
        addLeftSecondaryElements();
        return convertResultToIntArr();
    }

    private void iterateOverArraysUntilMajorHasElements() {
        while (majIter < majorArr.length && secIter < secondaryArr.length) {
            addSecondaryElementsBetweenMajorPairs();
            skipSecondaryWithinPair();
            addMajorPair();
        }
    }

    private void addLeftSecondaryElements() {
        for (int i = secIter; i < secondaryArr.length; i++) {
            result.add(secondaryArr[i]);
        }
    }

    private void addSecondaryElementsBetweenMajorPairs() {
        while (secondaryArr[secIter] < majorArr[majIter]) {
            result.add(secondaryArr[secIter]);
            secIter++;
        }
    }

    private void skipSecondaryWithinPair() {
        while (secIter < secondaryArr.length
                && majorArr[majIter + 1] >= secondaryArr[secIter]) {
            secIter++;
        }
    }

    private void addMajorPair() {
        result.add(majorArr[majIter]);
        result.add(majorArr[majIter + 1]);
        majIter += 2;
    }

    private int[] convertResultToIntArr() {
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
