// Lefei (Sebastian) Liu
// 05/13/2021
// CSE143
// TA: Randair Porter
// Homework 4 (bonus)
//
// Class HangmanManager2 extends HangmanManager and it improves two flaws
// in the original HangmanManager. One is that the program wins immediately
// when the user is down to one guess, the other is the internal data structure
// cannot be changed by the client

import java.util.*;

public class HangmanManager2 extends HangmanManager {
    private Set<String> protectedWords; // protected version of the set of current words being
    // considered
    private Set<Character> protectedGuesses; // protected version of set of characters the player
    // has guessed
    private Set<String> oldWords; // the old set of words being considered
    private Set<Character> oldGuesses; // the old set of characters the player has guessed

    // constructs a hangman manager and initializes words and guesses
    public HangmanManager2(Collection<String> dictionary, int length, int max) {
        super(dictionary, length, max);
        protectedWords = Collections.unmodifiableSet(super.words());
        protectedGuesses = Collections.unmodifiableSet(super.guesses());
        oldWords = super.words();
        oldGuesses = super.guesses();
    }

    // returns a protected version of the words being considered
    public Set<String> words() {
        if (super.words() != oldWords) {
            oldWords = super.words();
            protectedWords = Collections.unmodifiableSet(super.words());
        }
        return protectedWords;
    }

    // returns a protected version of the guessed being made
    public Set<Character> guesses() {
        if (super.guesses() != oldGuesses) {
            oldGuesses = super.guesses();
            protectedGuesses = Collections.unmodifiableSet(super.guesses());
        }
        return protectedGuesses;
    }

    // sets the words being considered to the first word that doesn't include the guess
    // when the user has only one guess left
    public int record(char guess) {
        if (super.guessesLeft() == 1) {
            for (String word : super.words()) {
                if (!word.contains(guess + "")) {
                    super.words().clear();
                    super.words().add(word);
                    return super.record(guess);
                }
            }
        }
        return super.record(guess);
    }
}
