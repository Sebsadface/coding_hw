package solvers;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.BellmanFordSolver;
import main.Parser;

import java.util.HashMap;
import java.util.List;

import static cse332.graph.GraphUtil.getCycle;

public class OutSequential implements BellmanFordSolver {

    @SuppressWarnings("ManualArrayCopy")
    public List<Integer> solve(int[][] adjMatrix, int source) {
        List<HashMap<Integer,Integer>> g = Parser.parse(adjMatrix);
        int[]d1 = new int[g.size()];
        int[]d2 = new int[g.size()];
        int[]p = new int[g.size()];

        // init distance arrays
        for(int i = 0; i < g.size(); i++) {
            d1[i] = d2[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }
        d1[source] = d2[source] = 0;

        for (int i = 0; i < g.size(); i++) {
            for (int v = 0; v < g.size(); v++) {
                d2[v] = d1[v];
            }

            for  (int v = 0; v < g.size(); v++) {
                for (Integer w : g.get(v).keySet()) {
                    if ((d2[v] != Integer.MAX_VALUE) && (g.get(v).get(w) != Integer.MAX_VALUE) && (d1[w] > d2[v] + g.get(v).get(w))) {
                        d1[w] = d2[v] + g.get(v).get(w);
                        p[w] = v;
                    }
                }
            }
        }

        return getCycle(p);
    }

}