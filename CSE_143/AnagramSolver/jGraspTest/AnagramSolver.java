import java.util.*;

public class AnagramSolver {
    Map<String, LetterInventory> inventoryMap;

    public AnagramSolver(List<String> list) {
        inventoryMap = new HashMap<>();
        for (String word : list) {
            inventoryMap.put(word, new LetterInventory(word));
        }
    }

    public void print(String s, int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        Stack<String> anagrams = new Stack<>();
        LetterInventory string = new LetterInventory(s);
        for (String word : inventoryMap.keySet()) {
         if (string.subtract(inventoryMap.get(word)) == null) {
            inventoryMap.remove(word);
            }
        print(string, anagrams, max);
    }
    }

    private void print(LetterInventory string, Stack<String> anagrams, int max) {
        if (string.isEmpty() && max < 0) {
            System.out.println(anagrams);
        }
        for (String word : inventoryMap.keySet()) {
            if (string.subtract(inventoryMap.get(word)) != null) {
                anagrams.push(word);
                string = string.subtract(inventoryMap.get(word));
                print(string, anagrams, max - 1);
                anagrams.pop();
            }
        }
    }
}
