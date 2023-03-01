package hasOver;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class HasOver {
    /**
     * Use the ForkJoin framework to write the following method in Java.
     *
     * Returns true if arr has any elements strictly larger than val.
     * For example, if arr is [21, 17, 35, 8, 17, 1], then
     * main.java.hasOver(21, arr) == true and main.java.hasOver(35, arr) == false.
     *
     * Your code must have O(n) work, O(lg n) span, where n is the length of arr, and actually use the sequentialCutoff
     * argument.
     */

    private static final ForkJoinPool POOL = new ForkJoinPool();
    private static int CUTOFF;
    private static int VAL;

    public static boolean hasOver(int val, int[] arr, int sequentialCutoff) {
        /* TODO: Edit this with your code */
        VAL = val;
        CUTOFF = sequentialCutoff;
        return paraHasOver(arr, arr.length);
    }

    /* TODO: Add a sequential method and parallel task here */
    private static boolean seqHasOver(int[] arr, int lo, int hi){
        for (int i = lo; i < hi; i++) {
            if (arr[i] > VAL) {
                return true;
            }
        }
        return false;
    }

    private static boolean paraHasOver(int[] arr, int hi) {
        return POOL.invoke(new hasOverTask(arr, 0, hi));
    }
    private static class hasOverTask extends RecursiveTask<Boolean> {
        int lo, hi;
        int[] arr;

        hasOverTask(int[] arr, int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.arr = arr;
        }

        @Override
        protected Boolean compute() {
            if (hi - lo <= CUTOFF) {
                return seqHasOver(arr, lo, hi);
            }

            int mid = lo + (hi - lo) / 2;

            hasOverTask left = new hasOverTask(arr, lo, mid);
            hasOverTask right = new hasOverTask(arr, mid, hi);

            left.fork();

            boolean rightRes = right.compute();
            boolean leftRes = left.join();

            return (rightRes || leftRes);

        }
    }

    private static void usage() {
        System.err.println("USAGE: HasOver <number> <array> <sequential cutoff>");
        System.exit(2);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            usage();
        }

        int val = 0;
        int[] arr = null;

        try {
            val = Integer.parseInt(args[0]);
            String[] stringArr = args[1].replaceAll("\\s*", "").split(",");
            arr = new int[stringArr.length];
            for (int i = 0; i < stringArr.length; i++) {
                arr[i] = Integer.parseInt(stringArr[i]);
            }
            System.out.println(hasOver(val, arr, Integer.parseInt(args[2])));
        } catch (NumberFormatException e) {
            usage();
        }

    }
}
