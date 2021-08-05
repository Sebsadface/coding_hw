// Lefei (Sebastian) Liu
// 03/05/2021
// CSE142
// TA: Emilia Borisova
// Homework 7 (Personality Test)
//
// This program processes a file of answers to the Keirsey Temperament Sorter. It converts the
// various A and B answers for each person into a sequence of B-percentages and then into a
// four-letter personality type.

import java.util.*;
import java.io.*;

public class Personality {
    public static final int dimensions = 4;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        intro();

        Scanner input = new Scanner(new File(getInput(console)));
        PrintStream output = new PrintStream(getOutput(console));

        while(input.hasNextLine()) {
            int[] aCounts = new int[dimensions];
            int[] bCounts = new int[dimensions];
            int[] bPercent = new int[dimensions];
            String name = input.nextLine();
            String answer = input.nextLine().toUpperCase();

            getCounts(answer, aCounts, bCounts);
            getPercent(aCounts, bCounts, bPercent);
            String type = getType(bPercent);
            output.println(name + ": " + Arrays.toString(bPercent) + " = " + type);
        }
    }

    // Prints a introduction of the program
    // Does not return any value
    public static void intro() {
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    // Reads in a user input of the input file name
    // Returns the input file name
    // Parameters:
    //    Scanner console - the scanner for user input
    public static String getInput(Scanner console) {
        System.out.print("input file name? ");
        return console.nextLine();
    }

    // Reads in a user input of the output file name
    // Returns the output file name
    // Parameters:
    //    Scanner console - the scanner for user input
    public static String getOutput(Scanner console) {
        System.out.print("output file name? ");
        return console.nextLine();
    }

    // Counts the number of A or B answers
    // Does not return any value
    // Parameters:
    //    String answer - a person's answers to the personality test
    //    int[] aCounts - the number of A answers on each dimension
    //    int[] bCounts - the number of B answers on each dimension
    public static void getCounts(String answer, int[] aCounts, int[] bCounts) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                char choice = answer.charAt(7 * i + j);

                if(choice == 'A') {
                    aCounts[(j + 1) / 2]++;
                } else if (choice == 'B') {
                    bCounts[(j + 1) / 2]++;
                }
            }
        }
    }

    // Calculates the percentage of B answers
    // Does not return any value
    // Parameters:
    //    int[] aCounts - the number of A answers on each dimension
    //    int[] bCounts - the number of B answers on each dimension
    //    int[] bPercent - the percentage of B answers
    public static void getPercent(int[] aCounts, int[] bCounts, int[] bPercent) {
        for (int i = 0; i < dimensions; i++) {
            bPercent[i] = (int) Math.round(100 * (double) bCounts[i] / (aCounts[i] + bCounts[i]));
        }
    }

    // Calculates the personality type of a person
    // Returns the personality type of a person
    // Parameters:
    //    int[] bPercent - the percentage of B answers
    public static String getType(int[] bPercent){
        char[] allTypes = {'E', 'I', 'S', 'N', 'T', 'F', 'J', 'P'};
        String type = "";

        for (int i = 0; i < dimensions; i++) {
            if (bPercent[i] < 50) {
                type += allTypes[i * 2];
            } else if (bPercent[i] > 50) {
                type += allTypes[i * 2 + 1];
            } else {
                type += 'X';
            }
        }
        return type;
    }
}