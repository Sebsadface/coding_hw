package filterEmpty;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class FilterEmpty {
    static ForkJoinPool POOL = new ForkJoinPool();
    static final int CUTOFF = 1;

    /**
     * Use the ForkJoin framework to write the following method in Java.
     *
     * Returns an array with the lengths of the non-empty strings from arr (in order)
     * For example, if arr is ["", "", "cse", "332", "", "hw", "", "7", "rox"], then
     * main.java.filterEmpty(arr) == [3, 3, 2, 1, 3].
     *
     * A parallel algorithm to solve this problem in O(lg n) span and O(n) work is the following:
     * (1) Do a parallel map to produce a bit set
     * (2) Do a parallel prefix over the bit set
     * (3) Do a parallel map to produce the output
     *
     * In lecture, we wrote parallelPrefix together, and it is included in the gitlab repository.
     * Rather than reimplementing that piece yourself, you should just use it. For the other two
     * parts though, you should write them.
     *
     * Do not bother with a sequential cutoff for this exercise, just have a base case that processes a single element.
     */
    public static int[] filterEmpty(String[] arr) {
        int[] bits = mapToBitSet(arr);

        int[] bitsum = ParallelPrefixSum.parallelPrefixSum(bits);

        return mapToOutput(arr, bits, bitsum);
    }

    public static int[] mapToBitSet(String[] arr) {
        /* TODO: Edit this with your code */
        int res[] = new int[arr.length];
        POOL.invoke(new mTBS(arr, res, 0, arr.length));
        return res;
    }

    /* TODO: Add a sequential method and parallel task here */

    public static int[] mapToOutput(String[] input, int[] bits, int[] bitsum) {
        /* TODO: Edit this with your code */
        int res[] = new int[(bitsum.length > 0) ? bitsum[bitsum.length -1] : 0];
        POOL.invoke(new mTOP(input, bitsum, res, 0, input.length));
        return res;
    }

    /* TODO: Add a sequential method and parallel task here */
    private static void seqMTBS(String arr[], int res[],  int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            res[i] = (arr[i].isEmpty() || arr[i] == null) ? 0 : 1;
        }
    }

    private static void seqMTOP(String input[], int[] bitsum, int res[],  int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (bitsum[i] > (i > 0 ? bitsum[i-1] : 0)) {
                res[bitsum[i]-1] = input[i].length();
            }
        }
    }

    private static class mTBS extends RecursiveAction {
        int[] res;
        String[] arr;
        int lo, hi;

        mTBS(String[] arr, int[] res, int lo, int hi) {
            this.arr = arr;
            this.res = res;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected void compute() {
            if (hi - lo <= CUTOFF) {
                seqMTBS(arr, res, lo, hi);
            } else {
                int mid = lo + (hi - lo) / 2;
                mTBS left = new mTBS(arr, res, lo, mid);
                mTBS right = new mTBS(arr, res, mid, hi);
                left.fork();
                right.compute();
                left.join();
            }
        }
    }

    private static class mTOP extends RecursiveAction {
        int[] res;
        int[] bitsum;
        String[] input;
        int lo, hi;

        mTOP(String[] input, int[] bitsum, int[] res, int lo, int hi) {
            this.input = input;
            this.bitsum = bitsum;
            this.res = res;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected void compute() {
            if (hi - lo <= CUTOFF) {
                seqMTOP(input, bitsum, res, lo, hi);
            } else {
                int mid = lo + (hi - lo) /2;
                mTOP left = new mTOP(input, bitsum, res, lo, mid);
                mTOP right = new mTOP(input, bitsum, res, mid, hi);
                left.fork();
                right.compute();
                left.join();
            }
        }
    }

    private static void usage() {
        System.err.println("USAGE: FilterEmpty <String array>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
        }

        String[] arr = args[0].replaceAll("\\s*", "").split(",");
        System.out.println(Arrays.toString(filterEmpty(arr)));
    }
}