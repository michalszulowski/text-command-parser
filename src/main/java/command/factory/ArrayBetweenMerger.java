package command.factory;

public interface ArrayBetweenMerger {
    /**
     * Input arrays must be sorted ascending and arr1 must have even size
     */
    int[] merge(int[] major, int[] secondary);
}
