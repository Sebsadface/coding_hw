// Lefei (Sebastian) Liu
// 02/02/2021
// CSE142
// TA: Emilia Borisova
// Homework 4 (Admissions)
//
// This program compares two applicants to determine which one seems like the stronger applicant.
// For each candidate either SAT or ACT scores plus a weighted GPA are needed.

import java.util.*;

public class Admit {
    public static void main (String[] args) {
        Scanner console = new Scanner(System.in);

        intro();

        System.out.println("Information for applicant #1:");
        double overallScore1 = getScores(console, getExamType(console)) + getGPA(console);
        System.out.println();

        System.out.println("Information for applicant #2:");
        double overallScore2 = getScores(console, getExamType(console)) + getGPA(console);
        System.out.println();
        getResults(overallScore1, overallScore2);
    }

    // Prints out an introduction of the program.
    public static void intro() {
        System.out.println("This program compares two applicants to");
        System.out.println("determine which one seems like the stronger");
        System.out.println("applicant.  For each candidate I will need");
        System.out.println("either SAT or ACT scores plus a weighted GPA.");
        System.out.println();
    }

    // Reads in which type of exam the applicant took
    // Returns the type of exam that the applicant took
    // Parameters:
    //    Scanner console - the scanner to use for input
    public static int getExamType(Scanner console) {
        System.out.print("    do you have 1) SAT scores or 2) ACT scores? ");
        return console.nextInt();
    }

    // Reads in and computes the applicant's exam scores
    // Returns the the exam score of the applicant
    // Parameters:
    //    Scanner console - the scanner to use for input
    //    int examType - the type of exam the applicant took
    public static double getScores(Scanner console, int examType) {
        double examScore;
        if (examType == 1) {
            examScore = getSAT(console);
        } else {
            examScore = getACT(console);
        }
        System.out.println("    exam score = " + round1(examScore));
        return examScore;
    }

    // Reads in and computes the applicant's GPA score
    // Returns the the GPA score of the applicant
    // Parameters:
    //    Scanner console - the scanner to use for input
    public static double getGPA(Scanner console) {
        System.out.print("    overall GPA? ");
        double overallGPA = console.nextDouble();
        System.out.print("    max GPA? ");
        double maxGPA = console.nextDouble();
        System.out.print("    Transcript Multiplier? ");
        double transcriptMultiplier = console.nextDouble();
        double gpaScore = gpaCalculator(overallGPA, maxGPA, transcriptMultiplier);
        System.out.println("    GPA score = " + round1(gpaScore));
        return gpaScore;
    }

    // Compare the two applicants' overall score and prints results
    // Returns nothing
    // Parameters:
    //    double overallScore1 - the overall score of the first applicant
    //    double overallScore2 - the overall score of the second applicant
    public static void getResults(double overallScore1, double overallScore2) {
        System.out.println("First applicant overall score  = " + round1(overallScore1));
        System.out.println("Second applicant overall score = " + round1(overallScore2));

        if (overallScore1 > overallScore2) {
            System.out.println("The first applicant seems to be better");
        } else if (overallScore1 < overallScore2) {
            System.out.println("The second applicant seems to be better");
        } else {
            System.out.println("The two applicants seem to be equal");
        }
    }

    // Reads in and computes the applicant's SAT score
    // Returns the the SAT score of the applicant
    // Parameters:
    //    Scanner console - the scanner to use for input
    public static double getSAT(Scanner console) {
        System.out.print("    SAT math? ");
        int satMath = console.nextInt();
        System.out.print("    SAT critical reading? ");
        int satReading = console.nextInt();
        System.out.print("    SAT writing? ");
        int satWriting = console.nextInt();
        return satCalculator(satMath, satReading, satWriting);
    }

    // Reads in and computes the applicant's ACT score
    // Returns the the ACT score of the applicant
    // Parameters:
    //    Scanner console - the scanner to use for input
    public static double getACT(Scanner console) {
        System.out.print("    ACT English? ");
        int actEnglish = console.nextInt();
        System.out.print("    ACT math? ");
        int actMath = console.nextInt();
        System.out.print("    ACT reading? ");
        int actReading = console.nextInt();
        System.out.print("    ACT science? ");
        int actScience = console.nextInt();
        return actCalculator(actEnglish, actMath, actReading, actScience);
    }

    // Returns the the SAT score with given grades
    // Parameters:
    //    int satMath - SAT math grades
    //    int satReading - SAT reading grades
    //    int satWriting - SAT writing grades
    public static double satCalculator(int satMath, int satReading, int satWriting) {
        return (2 * (double) satMath + satReading + satWriting) / 32;
    }

    // Returns the the ACT score with given grades
    // Parameters:
    //    int actEnglish - ACT English grades
    //    int actMath - ACT math grades
    //    int satReading - ACT reading grades
    //    int satScience - ACT science grades
    public static double actCalculator(int actEnglish, int actMath,
                                       int actReading, int actScience) {
        return (actEnglish + 2 * actMath + actReading + actScience) / 1.8;
    }

    // Returns the the GPA score with given information
    // Parameters:
    //    double overallGPA - actual GPA
    //    double maxGPA - maximum GPA
    //    double transcriptMultiplier - transcript multiplier
    public static double gpaCalculator(double overallGPA, double maxGPA,
                                       double transcriptMultiplier) {
        return overallGPA / maxGPA * 100 * transcriptMultiplier;
    }

    // Returns the given number rounded to one decimal place
    // Parameters:
    //    double n - the number to round
    public static double round1(double n) {
        return Math.round(n * 10.0) / 10.0;
    }
}
