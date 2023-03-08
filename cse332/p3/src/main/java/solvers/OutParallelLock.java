package solvers;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.BellmanFordSolver;
import main.Parser;
import paralleltasks.ArrayCopyTask;
import paralleltasks.RelaxOutTaskBad;
import paralleltasks.RelaxOutTaskLock;

import java.util.HashMap;
import java.util.List;

import static cse332.graph.GraphUtil.getCycle;

public class OutParallelLock implements BellmanFordSolver {

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
            d2 = ArrayCopyTask.copy(d1);
            RelaxOutTaskLock.parallel(d1,d2,p,g);
        }

        return getCycle(p);
    }

}