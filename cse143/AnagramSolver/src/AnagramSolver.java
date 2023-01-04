// Lefei (Sebastian) Liu
// 05/20/2021
// CSE143
// TA: Randair Porter
// Homework 6 (AnagramSolver)
//
// Class AnagramSolver can be used to find all combinations of words that have the same letters
// as a given phrase using the given dictionary

import java.util.*;

public class AnagramSolver {
    // a map of words in the dictionary and their corresponding inventories
    private Map<String, LetterInventory> inventoryMap;
    private List<String> dictionary; // the dictionary AnagramSolver is currently using

    // post: constructs a AnagramSolver that uses the given list as its dictionary
    //       processes the dictionary and computes all the inventories
    //       the original list is not changed in anyway
    //       the dictionary is a assumed to be nonempty collection of nonempty sequences
    //       of letters and contains no duplicates.
    public AnagramSolver(List<String> list) {
        initializeMap(list);
        dictionary = list;
    }

    // pre: max >= 0 (throws IllegalArgumentException if not)
    // post: searches for and prints all the anagrams of the given string, which include at
    //       most max words or an unlimited number of words if max is 0
    //       prints "[]" if inventory if empty
    //       case insensitive (String s can be either upper or lower case)
    public void print(String s, int max) {
        checkArgument(max);
        Stack<String> anagrams = new Stack<>();
        LetterInventory inventory = new LetterInventory(s);
        print(pruneDictionary(inventory), inventory, anagrams, max);
    }

    // post: searches for and prints out all the anagrams in square brackets separated by commas
    //        each possibility is printed on a new line
    //        prints "[]" if inventory if empty
    private void print(List<String> relevantWords, LetterInventory inventory, Stack<String> anagrams, int max) {
        if (inventory.isEmpty()) {
            System.out.println(anagrams);
        }
        if (max == 0 || max != anagrams.size()) {
            for (String word : relevantWords) {
                LetterInventory currentInventory = inventory.subtract(inventoryMap.get(word));
                if (currentInventory != null) {
                    anagrams.push(word);
                    print(relevantWords, currentInventory, anagrams, max);
                    anagrams.pop();
                }
            }
        }
    }

    // post: processes and stores the words in the given dictionary
    //       and computes all the inventories
    private void initializeMap(List<String> dictionary) {
        inventoryMap = new HashMap<>();
        for (String word : dictionary) {
            inventoryMap.put(word, new LetterInventory(word));
        }
    }

    // post: checks whether max >= 0
    //       throws IllegalArgumentException if not
    private void checkArgument(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max: " + max);
        }
    }

    // post: reduces the dictionary to a smaller dictionary of relevant words
    //       and returns the new dictionary
    private List<String> pruneDictionary(LetterInventory inventory) {
        List<String> relevantWords = new ArrayList<>();
        for (String word : dictionary) {
            if (inventory.subtract(inventoryMap.get(word)) != null) {
                relevantWords.add(word);
            }
        }
        return relevantWords;
    }
}
