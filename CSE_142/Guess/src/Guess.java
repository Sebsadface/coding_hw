// Lefei (Sebastian) Liu
// 02/09/2021
// CSE142
// TA: Emilia Borisova
// Homework 5 (Guessing Game)
//
// This program allows you to play a guessing game. I will think of a number between 1 and 100 and
// will allow you to guess until you get it. For each guess, I will tell you whether the right
// answer is higher or lower than your guess.

import java.util.*;

public class Guess {
    public static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random r = new Random();

        int totalGames = 0;
        int totalGuesses = 0;
        int currentGuesses;
        int bestGame = 9999;

        intro();
        String play = "Y"; // priming a variable
        while (play.startsWith("Y")) {
            currentGuesses = playGame(console, r);
            bestGame = Math.min(bestGame, currentGuesses);
            totalGuesses += currentGuesses;
            totalGames++;
            System.out.print("Do you want to play again? ");
            play = console.next().toUpperCase();
            System.out.println();
        }
        getResults(totalGames, totalGuesses, bestGame);
    }

    // Prints a introduction of the program
    // Does not return any value
    public static void intro() {
        System.out.println("This program allows you to play a guessing game.");
        System.out.println("I will think of a number between 1 and");
        System.out.println(MAX_VALUE + " and will allow you to guess until");
        System.out.println("you get it.  For each guess, I will tell you");
        System.out.println("whether the right answer is higher or lower");
        System.out.println("than your guess.");
        System.out.println();
    }

    //plays a single game
    // Returns the total number of guesses the user takes to win
    // Parameters:
    //    Scanner console - the scanner for user input
    //    Random rand - generates random numbers
    public static int playGame(Scanner console, Random r) {
        System.out.println("I'm thinking of a number between 1 and " + MAX_VALUE + "...");
        int answer = r.nextInt(MAX_VALUE) + 1;
        int guess = 0;
        int totalGuesses = 0;
        while (guess != answer) {
            System.out.print("Your guess? ");
            guess = console.nextInt();
            if (answer > guess) {
                System.out.println("It's higher.");
            } else if (answer < guess) {
                System.out.println("It's lower.");
            }
            totalGuesses++;
        }
        if (totalGuesses == 1) {
            System.out.println("You got it right in " + totalGuesses + " guess");
        } else {
            System.out.println("You got it right in " + totalGuesses + " guesses");
        }
        return totalGuesses;
    }

    // Calculates and prints the results of all the rounds played
    // Does not return any value
    // Parameters:
    //    int totalGames - the total number of games played
    //    int totalGuesses - the total number of guesses
    //    int bestGame - the least number of guesses took to win in a single game
    public static void getResults (int totalGames, int totalGuesses, int bestGame) {
        System.out.println("Overall results:");
        System.out.println("    total games   = " + totalGames);
        System.out.println("    total guesses = " + totalGuesses);
        System.out.println("    guesses/game  = " + round1(((double)totalGuesses / totalGames)));
        System.out.println("    best game     = " + bestGame);
    }

    // Returns the given number rounded to one decimal place.
    // Parameters:
    //     double n - the number to round
    public static double round1(double n) {
        return Math.round(n * 10.0) / 10.0;
    }
}