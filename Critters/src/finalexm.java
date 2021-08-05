import java.io.*;
import java.util.*;

public class finalexm {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner data = new Scanner("Jan 1 2 3 4 5 6");
//        Scanner data1 = new Scanner("Feb 2 2 9 4 9 4");
//        Scanner data2 = new Scanner("Apr");
//        switchData(data);
//        switchData(data1);
//        switchData(data2);

//        Scanner input = new Scanner (new File("1.txt"));
//        int max = analyzeParagraphs(input);

//        int[] list1 = {1};
//        int[] list2 = {1, 1};
//        int[] list3 = {1, 1, 2, 2, 3, 3};
//        int[] list4 = {2, 2, 55, 55, 33, 33, 33, 33};
//        int[] list5 = {2, 2, 5, 5, 6, 7, 3, 4};
//        int[] list6 = {3, 3, 2, 4, 4};
//        int[] list7 = {2, 2, 1, 1, 1};
//        System.out.println(isAllPairs(list1));
//        System.out.println(isAllPairs(list2));
//        System.out.println(isAllPairs(list3));
//        System.out.println(isAllPairs(list4));
//        System.out.println(isAllPairs(list5));
//        System.out.println(isAllPairs(list6));
//        System.out.println(isAllPairs(list7));

//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(18);
//        list.add(7);
//        list.add(4);
//        list.add(24);
//        list.add(11);
//        split(list);
//        System.out.println(list);

//        int[] list1 = {2, 8, 17, 3, 9, 0};
//        int[] list2 = {103, 72, -8, 15, 42};
//        System.out.println(Arrays.toString(surroundWithN(list1, list2, 3)));

        System.out.println(acronym("   automatic   teller   machine  "));
        System.out.println(acronym("personal identification number"));
        System.out.println(acronym("computer science"));
        System.out.println(acronym("merry-go-round"));
        System.out.println(acronym("All my Children"));
        System.out.println(acronym("Troubled Assets Relief Program"));
        System.out.println(acronym("--quite-- confusing - punctuation-"));
        System.out.println(acronym("  loner  "));

    }

    public static String acronym(String str) {
        String result = "";
        char a;
        char b;
        for (int i = 0; i < str.length() - 1; i++) {
            a = str.charAt(i);
            b = str.charAt(i + 1);
            if (a != ' ' && a != '-' && i == 0) {
                result += a;
            }
            if ((a == ' ' || a == '-') && b != ' ' && b != '-') {
                result += b;
            }
        }
        return result.toUpperCase();
    }

//    public static int[] surroundWithN(int[] list1, int[] list2, int n) {
//        int[] result = new int[n * 2 + list1.length];
//        for (int i = 0; i < n; i++) {
//            result[i] = list2[i];
//            result[n + list1.length + i] = list2[i];
//        }
//        for (int i = 0; i < list1.length; i++) {
//            result[n + i] = list1[i];
//        }
//        return result;
//    }

//    public static void split(ArrayList list) {
//        int length = list.size();
//        for (int i = 0; i < length; i++) {
//            list.add(i * 2 + 1, (int) list.get(i * 2) / 2);
//            list.set(i * 2, (int) list.get(i * 2) - (int) list.get(i * 2 + 1));
//        }
//    }

//    public static boolean isAllPairs(int[] list) {
//        if (list.length % 2 == 0) {
//            for (int i = 0; i < list.length / 2; i++) {
//                if (list[2 * i] != list[2 * i + 1]) {
//                    return false;
//                }
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public static int analyzeParagraphs(Scanner input) {
//        int paragraphLength = 0;
//        int max = 0;
//       while (input.hasNextLine()) {
//           String line = input.nextLine();
//           if (line.equals("<p>")) {
//               System.out.println(paragraphLength + "-line paragraph");
//               max = Math.max(max, paragraphLength);
//               paragraphLength = 0;
//           } else {
//               paragraphLength++;
//           }
//       }
//       return max;
//    }

//    public static void switchData(Scanner data) {
//        System.out.print(data.next());
//        if (data.hasNext()) {
//            int[] reverse = new int[6];
//            for (int i = 0; i < 3; i++) {
//                reverse[2 * i + 1] = data.nextInt();
//                reverse[2 * i] = data.nextInt();
//            }
//            for (int i = 0; i < 6; i++) {
//                System.out.print(" " + reverse[i]);
//            }
//        }
//        System.out.println();
//    }
}
