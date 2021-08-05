import java.io.*;
import java.util.*;

public class SebsTest {
    public static void main(String[] args) throws FileNotFoundException {
      mystery1(4);
    }
    public static int[][] mystery1(int n) {
      int [][] result = new int[n][2 * n - 1];
      for (int i = 0; i < result.length; i++) {
         for (int j = 0; j < result[i].length; j++) {
            result[i][j] = i + j;
         }
      }
      return result;
    }
}
