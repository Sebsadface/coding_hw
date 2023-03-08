package main;

import cse332.exceptions.NotYetImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Parser {

    /**
     * Parse an adjacency matrix into an adjacency list.
     * @param adjMatrix Adjacency matrix
     * @return Adjacency list
     */
    public static List<HashMap<Integer, Integer>> parse(int[][] adjMatrix) {
        List<HashMap<Integer, Integer>> adjList = new ArrayList<>(adjMatrix.length);
        for (int i = 0; i < adjMatrix.length; i++) {
            adjList.add(i, new HashMap<>());
            for (int j = 0; j < adjMatrix[0].length; j++) {
                adjList.get(i).put(j, adjMatrix[i][j]);
            }
        }
        return adjList;
    }

    /**
     * Parse an adjacency matrix into an adjacency list with incoming edges instead of outgoing edges.
     * @param adjMatrix Adjacency matrix
     * @return Adjacency list with incoming edges
     */
    public static List<HashMap<Integer, Integer>> parseInverse(int[][] adjMatrix) {
        List<HashMap<Integer, Integer>> adjList = new ArrayList<>(adjMatrix.length);
        for (int i = 0; i < adjMatrix[0].length; i++) {
            adjList.add(i, new HashMap<>());
            for (int j = 0; j < adjMatrix.length; j++) {
                adjList.get(i).put(j, adjMatrix[i][j]);
            }
        }
        return adjList;
    }

}
