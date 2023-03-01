package getLeftMostIndex;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class GetLeftMostIndex {
    /**
     * Use the ForkJoin framework to write the following method in Java.
     *
     * Returns the index of the left-most occurrence of needle in haystack (think of needle and haystack as
     * Strings) or -1 if there is no such occurrence.
     *
     * For example, main.java.getLeftMostIndex("cse332", "Dudecse4ocse332momcse332Rox") == 9 and
     * main.java.getLeftMostIndex("sucks", "Dudecse4ocse332momcse332Rox") == -1.
     *
     * Your code must actually use the sequentialCutoff argument. You may assume that needle.length is much
     * smaller than haystack.length. A solution that peeks across subproblem boundaries to decide partial matches
     * will be significantly cleaner and simpler than one that does not.
     */

    private static ForkJoinPool POOL = new ForkJoinPool();
    private static char[] NEEDLE;
    private static char[] HAYSTACK;
    private static int CUTOFF;

    public static int getLeftMostIndex(char[] needle, char[] haystack, int sequentialCutoff) {
        /* TODO: Edit this with your code */
        NEEDLE = needle;
        CUTOFF = sequentialCutoff;
        HAYSTACK = haystack;
        return parallel(haystack.length);
    }

    /* TODO: Add a sequential method and parallel task here */

    private static int sequential(int lo, int hi) {
        while (lo < hi && lo + NEEDLE.length <= HAYSTACK.length) {
            int i = 0;
            while (NEEDLE[i] == HAYSTACK[lo+i]) {
                if (i == NEEDLE.length - 1) {
                    return lo;
                }
                i++;
            }
            lo++;
        }
        return -1;
    }

    private static int parallel(int hi) {
        return POOL.invoke(new leftMostIndexClass(0, hi));
    }

    private static class leftMostIndexClass extends RecursiveTask<Integer> {
        int lo, hi;

        public leftMostIndexClass(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected Integer compute() {
            if (hi - lo <= CUTOFF) {
                return sequential(lo, hi);
            }
            int mid = lo +  (hi - lo) / 2;
            leftMostIndexClass left = new leftMostIndexClass(lo, mid);
            leftMostIndexClass right = new leftMostIndexClass(mid, hi);
            right.fork();
            int leftResult = left.compute();
            int rightResult = right.join();
            return leftResult == -1 ? rightResult : leftResult;
        }

    }
    private static void usage() {
        System.err.println("USAGE: GetLeftMostIndex <needle> <haystack> <sequential cutoff>");
        System.exit(2);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            usage();
        }

        char[] needle = args[0].toCharArray();
        char[] haystack = args[1].toCharArray();
        try {
            System.out.println(getLeftMostIndex(needle, haystack, Integer.parseInt(args[2])));
        } catch (NumberFormatException e) {
            usage();
        }
    }
}
