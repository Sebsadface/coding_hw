// Lefei (Sebastian) Liu
// 04/08/2021
// CSE143
// TA: Randair Porter
// Homework 1 (Letter Inventory)
//
// Class LetterInventory can be used to keep track of an inventory of letters of the alphabet that
// appeared in a given string.

public class LetterInventory {
    private int[] inventory; // an inventory of alphabetic letters
    private int size;        // current sum of all the counts in this inventory

    public static final int NUMBER_OF_ITEMS = 'z' - 'a' + 1;

    // post: construct an inventory with given data
    public LetterInventory(String data) {
        inventory = new int[NUMBER_OF_ITEMS];
        countLetters(data);
        size = getSize();
    }

    // pre : 'a' <= letter <= 'z' (throws IllegalArgumentException if not) ||
    //       'A' <= letter <= 'Z' (throws IllegalArgumentException if not)
    // post: returns a count of how many this letter are in the inventory
    public int get(char letter) {
        checkArguments(letter, 0);
        return inventory[Character.toLowerCase(letter) - 'a'];
    }

    // pre : 'a' <= letter <= 'z' && value > 0 (throws IllegalArgumentException if not) ||
    //       'A' <= letter <= 'Z' && value > 0 (throws IllegalArgumentException if not)
    // post: sets the count for the given letter to the given value
    public void set(char letter, int value) {
        checkArguments(letter, value);
        int index = Character.toLowerCase(letter) - 'a';
        int oldValue = inventory[index];
        inventory[index] = value;
        size = size - oldValue + value;
    }

    // post: returns the sum of all the counts in this inventory
    public int size() {
        return size;
    }

    // post: returns true if this inventory is empty (all counts are 0),
    //       false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns a String representation of the inventory with the letters all in lowercase and
    //       in sorted order and surrounded by square brackets.
    public String toString() {
        String answer = "[";
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            for (int j = 0; j < inventory[i]; j++) {
                answer += (char) (i + 'a');
            }
        }
        return answer + "]";
    }

    // post: constructs and returns a new LetterInventory object that represents the sum of this
    //       letter inventory and the other given LetterInventory
    public LetterInventory add(LetterInventory other) {
        LetterInventory sum = new LetterInventory("");
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            sum.inventory[i] = inventory[i] + other.inventory[i];
        }
        sum.size = size + other.size;
        return sum;
    }

    // post: constructs and returns a new LetterInventory object that represents the result of
    //       subtracting the other inventory from this inventory
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory sum = new LetterInventory("");
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            sum.inventory[i] = inventory[i] - other.inventory[i];
            if (sum.inventory[i] < 0) {
                return null;
            }
        }
        sum.size = size - other.size;
        return sum;
    }

    // post: computes and returns the sum of all the counts in this inventory
    private int getSize() {
        int size = 0;
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            size += inventory[i];
        }
        return size;
    }

    // post: counts and stores the number of alphabetic letters in the given data to this inventory
    private void countLetters(String data) {
        data = data.toLowerCase();
        for (int i = 0; i < data.length(); i++) {
            char currentChar = data.charAt(i);
            if (currentChar >= 'a' && currentChar <= 'z') {
                inventory[currentChar - 'a']++;
            }
        }
    }

    // post: check whether 'a' <= letter <= 'z' && value > 0 or 'A' <= letter <= 'Z' && value > 0
    //       throws IllegalArgumentException if not
    private void checkArguments(char letter, int value) {
        letter = Character.toLowerCase(letter);
        if (letter < 'a' || letter > 'z' || value < 0) {
            throw new IllegalArgumentException("letter: " + letter + "  value: " + value);
        }
    }

}
