import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

public class SebsTest {
    public static void main(String[] args) {
        LetterInventory l = new LetterInventory("aaaAAAfsdFFEW");
        System.out.println(l.size());
        System.out.println(l.isEmpty());
        System.out.println(l);
        System.out.println(l.get(')'));
//        int[] inventory = new int[26];
//        String data = "Hello World!!@#";
//        System.out.println(data + (char)97);
//        data = data.toLowerCase();
//        for (int i = 0; i < data.length(); i++) {
//            char currentChar = data.charAt(i);
//            if (currentChar >= 96 && currentChar <= 122) {
//                inventory[currentChar - 97]++;
//            }
//        }
//        int size = 0;
//        for (int i = 0; i < 26; i++) {
//            size += inventory[i];
//        }
//
//        System.out.println(size);
//        String answer = "[";
//        for (int i = 0; i < 26; i++) {
//            for (int j = 0; j < inventory[i]; j++) {
//                answer += (char) (i + 97);
//                System.out.println(answer);
//            }
//        }
//        System.out.println( answer + "]");
//
    }
}
