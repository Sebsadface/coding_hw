import java.util.*;
public class SebsTest {
    public static void main(String[] args) {
        String[] haha = {"abcd", "efg", "high"};
        System.out.println(something(haha));
    }

    public static String something(String[] haha) {
        if (haha.length > 0) {
            return "yes";
        } else {
            return "no";
        }
    }
}
