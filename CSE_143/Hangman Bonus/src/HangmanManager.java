// Lefei (Sebastian) Liu
// 04/29/2021
// CSE143
// TA: Randair Porter
// Homework 4 (Evil Hangman)
//
// Class Hangman Manager can be used to keep track of the state of a game of hangman where
// picking a word is delayed until it is forced to

import java.util.*;

public class HangmanManager {
    private Set<String> words; // the set of words hangman manager is considering
    private Set<Character> guesses; // the set of characters the player has guessed
    private String pattern; // the current pattern to be displayed for the hangman game
    private int guessesLeft; // the number of guesses left

    // pre: length >= 1 && max >= 0 (throws IllegalArgumentException if not)
    // post: constructs a hangman manager and initialize the state of the game with
    //       given dictionary, target word length, and maximum wrong guesses allowed
    //       the set of words hangman manager will be considering is initialized to
    //       all the words which have the given length in the dictionary
    public HangmanManager(Collection<String> dictionary, int length, int max) {
        checkArgument(length, max);
        guessesLeft = max;
        guesses = new TreeSet<>();
        initializePattern(length);
        initializeWords(dictionary, length);
    }

    // post: returns the current set of words being considered by the hangman manager
    public Set<String> words() {
        return words;
    }

    // post: returns the number of guesses the player has left
    public int guessesLeft() {
        return guessesLeft;
    }

    // post: returns the current set of letters that has been guesses by the player
    public Set<Character> guesses() {
        return guesses;
    }

    // pre: the set of words being considered is not empty
    //      (throws IllegalStateException if is empty)
    // post: returns the current pattern to be displayed for the hangman game
    //       the characters already guessed are taken into consideration
    public String pattern() {
        checkWords();
        return pattern;
    }

    // pre: guessesLeft >= 1 && the set of words being considered is not empty
    //      (throws IllegalStateException if not)
    //      char guess has not been guessed before
    //      (throws IllegalArgumentException if it was guessed)
    //       the character guess is assumed to be lowercase letter
    // post: decides and updates the pattern and the set of words to use going forward
    //       updates the number of guesses left
    //       returns the number of occurrences of the guessed letter in the new pattern
    public int record(char guess) {
        checkState();
        checkArgument(guess);
        guesses.add(guess);
        Map<String, Set<String>> cheater = new TreeMap<>();
        for (String word : words) {
            String patternKey = getPattern(word, guess);
            mappingWords(cheater, patternKey, word);
        }
        pickPattern(cheater);
        words = cheater.get(pattern);
        return getOccurrences(guess);
    }

    // post: check whether length >= 1 && max >= 0
    //       (throws IllegalArgumentException if not)
    private void checkArgument(int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException("word length: " + length + " max guesses: " + max);
        }
    }

    // post: checks whether the answer has not been guessed before
    //       (throws IllegalArgumentException if not)
    //       the character guess is assumed to be lowercase letter
    private void checkArgument(char guess) {
        if (guesses.contains(guess)) {
            throw new IllegalArgumentException("previous guesses: " + guesses);
        }
    }

    // post: checks whether the set of words being considered by the hangman manager is not empty
    //       (throws IllegalStateException if not)
    private void checkWords() {
        if (words.isEmpty()) {
            throw new IllegalStateException("no word is being considered");
        }
    }

    // post: checks whether the set of words being considered by the hangman manager is not empty
    //       && guessesLeft >= 1
    //       (throws IllegalStateException if not)
    private void checkState() {
        if (guessesLeft < 1 || words.isEmpty()) {
            throw new IllegalStateException("guesses left: " + guessesLeft +
                                            " any word under consideration: " + words.isEmpty());
        }
    }

    // post: initializes the pattern to be displayed for the hangman game to be dashes and spaces
    private void initializePattern(int length) {
        pattern = "-";
        for (int i = 1; i < length; i++) {
            pattern += " -";
        }
    }

    // post: initializes the set of words to be considered to be the words in the dictionary that
    //       has the given length
    private void initializeWords(Collection<String> dictionary, int length) {
        words = new TreeSet<>();
        for (String word : dictionary) {
            if (word.length() == length) {
                words.add(word);
            }
        }
    }

    // post: returns the pattern of the given word according to the guess
    //       the character guess is assumed to be lowercase letter
    private String getPattern(String word, char guess) {
        String patternKey = pattern;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                patternKey = patternKey.substring(0, i * 2) + guess +
                        patternKey.substring(i * 2 + 1);
            }
        }
        return patternKey;
    }

    // post: sets the pattern to be the pattern that fits the largest family of words
    private void pickPattern(Map<String, Set<String>> cheater) {
        int max = 0;
        for (String patternKey : cheater.keySet()) {
            if (max < cheater.get(patternKey).size()) {
                max = cheater.get(patternKey).size();
                pattern = patternKey;
            }
        }
    }

    // post: returns the number of occurrences of the given letter in the current pattern
    //       returns 0 if the given letter is not in the pattern and subtract guesses left by one
    //       the character guess is assumed to be lowercase letter
    private int getOccurrences(char guess) {
        int occurrences = 0;
        if (pattern.contains(guess + "")) {
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) == guess) {
                    occurrences++;
                }
            }
        } else {
            guessesLeft--;
        }
        return occurrences;
    }

    // post: maps the given word to a patternKey according to the pattern it shows
    //       creates a new patternKey in the map if the pattern has not been seen before
    private void mappingWords(Map<String, Set<String>> cheater, String patternKey, String word) {
        if (!cheater.containsKey(patternKey)) {
            cheater.put(patternKey, new TreeSet<>());
        }
        cheater.get(patternKey).add(word);
    }
}
