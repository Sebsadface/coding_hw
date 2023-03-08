package paralleltasks;

import cse332.exceptions.NotYetImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RelaxOutTaskBad extends RecursiveAction {

    public static final ForkJoinPool pool = new ForkJoinPool();
    public static final int CUTOFF = 1;
    private static int[] d1;
    private static int[] d2;
    private static int[] p;
    private int lo;
    private int hi;
   private static List<HashMap<Integer,Integer>> g;

    public RelaxOutTaskBad(int[] d1, int[] d2, int[] p, List<HashMap<Integer,Integer>> g, int lo, int hi) {
        RelaxOutTaskBad.d1 = d1;
        RelaxOutTaskBad.d2 = d2;
        RelaxOutTaskBad.p = p;
        RelaxOutTaskBad.g = g;
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
        for (Integer w: g.get(v).keySet()) {
            if ((d2[v] != Integer.MAX_VALUE) && (g.get(v).get(w) != Integer.MAX_VALUE) && (d1[w] > d2[v] + g.get(v).get(w))) {
                d1[w] = d2[v] + g.get(v).get(w);
                p[w] = v;
            }
        }
    }

    public static void parallel(int[] d1, int[] d2, int[] p, List<HashMap<Integer,Integer>> g) {
        pool.invoke(new RelaxOutTaskBad(d1, d2, p, g, 0, g.size()));
    }

}
