package getLongestSequence;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class GetLongestSequence {
    /**
     * Use the ForkJoin framework to write the following method in Java.
     *
     * Returns the length of the longest consecutive sequence of val in arr.
     * For example, if arr is [2, 17, 17, 8, 17, 17, 17, 0, 17, 1], then
     * getLongestSequence(17, arr) == 3 and getLongestSequence(35, arr) == 0.
     *
     * Your code must have O(n) work, O(lg n) span, where n is the length of arr, and actually use the sequentialCutoff
     * argument. We have provided you with an extra class SequenceRange. We recommend you use this class as
     * your return value, but this is not required.
     */

    private static final ForkJoinPool POOL = new ForkJoinPool();
    private static int CUTOFF;

    private static int VAL;
    public static int getLongestSequence(int val, int[] arr, int sequentialCutoff) {
        /* TODO: Edit this with your code */
        VAL = val;
        CUTOFF = sequentialCutoff;
        SequenceRange res = parallel(arr, arr.length);
        return res.longestRange;
    }

    /* TODO: Add a sequential method and parallel task here */
    private static SequenceRange sequential(int[] arr, int lo, int hi) {
        SequenceRange res = new SequenceRange(0,0,0, hi - lo);
        int count = 0;

        // get longest
        for (int i = lo; i < hi; i++) {
            if (arr[i] == VAL) {
                count++;
            } else {
                count = 0;
            }
            res.longestRange = Math.max(count, res.longestRange);
        }

        // get left
        for (int i = lo; i < hi; i++) {
            if (arr[i] == VAL) {
                res.matchingOnLeft++;
            } else {
                break;
            }
        }

        // get right
        for (int i = hi - 1; i >= lo; i--) {
            if (arr[i] == VAL) {
                res.matchingOnRight++;
            } else {
                break;
            }
        }

        return res;
    }

    private static SequenceRange parallel(int[] arr, int hi) {
        return POOL.invoke(new getLSTask(arr, 0, hi));
    }

    private static class getLSTask extends RecursiveTask<SequenceRange> {
        int lo, hi;
        int[] arr;

        getLSTask(int[] arr, int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.arr = arr;
        }

        @Override
        protected SequenceRange compute() {
            if (hi - lo <= CUTOFF) {
                return sequential(arr, lo, hi);
            } else {
                int mid = lo + (hi - lo) / 2;
                getLSTask left = new getLSTask(arr, lo, mid);
                getLSTask right = new getLSTask(arr, mid, hi);
                left.fork();
                SequenceRange rightRes = right.compute();
                SequenceRange leftRes = left.join();
                return combine(leftRes, rightRes);
            }
        }
    }

    private static SequenceRange combine(SequenceRange leftRes, SequenceRange rightRes) {
        SequenceRange res = new SequenceRange(0,0,0, leftRes.length + rightRes.length);

        // get longest
        res.longestRange = Math.max(leftRes.longestRange, rightRes.longestRange);

        if (leftRes.length == leftRes.matchingOnLeft) {
            res.matchingOnLeft = leftRes.length + rightRes.matchingOnLeft;
        } else {
            res.matchingOnLeft = leftRes.matchingOnLeft;
        }

        // get right
        if (rightRes.length == rightRes.matchingOnRight) {
            res.matchingOnRight = rightRes.length + leftRes.matchingOnRight;
        } else {
            res.matchingOnRight = rightRes.matchingOnRight;
        }

        // get longest
        res.longestRange = Math.max(res.longestRange, res.matchingOnLeft);
        res.longestRange = Math.max(res.longestRange, res.matchingOnRight);
        res.longestRange = Math.max(res.longestRange, leftRes.matchingOnRight + rightRes.matchingOnLeft);

       return res;
    }


    private static void usage() {
        System.err.println("USAGE: GetLongestSequence <number> <array> <sequential cutoff>");
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
            System.out.println(getLongestSequence(val, arr, Integer.parseInt(args[2])));
        } catch (NumberFormatException e) {
            usage();
        }
    }
}