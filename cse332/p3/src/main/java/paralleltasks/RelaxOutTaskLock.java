package paralleltasks;

import cse332.exceptions.NotYetImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.ReentrantLock;

public class RelaxOutTaskLock extends RecursiveAction {

    public static final ForkJoinPool pool = new ForkJoinPool();
    public static final int CUTOFF = 1;
    private static int[] d1;
    private static int[] d2;
    private static int[] p;
    private final int lo;
    private final int hi;
    private static List<HashMap<Integer,Integer>> g;

    private ReentrantLock lk = new ReentrantLock();

    public RelaxOutTaskLock(int[] d1, int[] d2, int[] p, List<HashMap<Integer,Integer>> g, int lo, int hi) {
        RelaxOutTaskLock.d1 = d1;
        RelaxOutTaskLock.d2 = d2;
        RelaxOutTaskLock.p = p;
        RelaxOutTaskLock.g = g;
        this.lo = lo;
        this.hi = hi;
    }

    protected void compute() {
        if (hi - lo <= CUTOFF) {
            sequential(lo);
        } else {
            int mid = lo + (hi -lo) / 2;
            RelaxOutTaskBad left = new RelaxOutTaskBad(d1,d2,p,g,lo,mid);
            RelaxOutTaskBad right = new RelaxOutTaskBad(d1,d2,p,g, mid, hi);
            left.fork();
            right.compute();
            left.join();
        }
    }

    public void sequential(int v) {
        lk.lock();
        for (Integer w: g.get(v).keySet()) {
            if ((d2[v] != Integer.MAX_VALUE) && (g.get(v).get(w) != Integer.MAX_VALUE) && (d1[w] > d2[v] + g.get(v).get(w))) {
                d1[w] = d2[v] + g.get(v).get(w);
                p[w] = v;
            }
        }
        lk.unlock();
    }

    public static void parallel(int[] d1, int[] d2, int[] p, List<HashMap<Integer,Integer>> g) {
        pool.invoke(new RelaxOutTaskBad(d1, d2, p, g, 0, g.size()));
    }

}
