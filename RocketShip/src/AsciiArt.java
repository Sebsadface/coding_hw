// Lefei (Sebastian) Liu
// 19/1/2021
// CSE142
// TA: Emilia Borisova
// Homework 2 (Rocket Ship)
//
// This program will output a ASCII art figure which looks like a heart.

public class AsciiArt {
    public static void main(String[] args) {
        drawHumps();
        drawLines();
        drawBottom();
    }

    // prints the top of the heart
    public static void drawHumps() {
        for (int line = 1; line <= 3; line++) {
            for (int space = 1; space <= - 3 * line + 15; space++) {
                System.out.print(" ");
            }
            for (int parenthesis = 1; parenthesis <= 3 * line + 2; parenthesis++) {
                System.out.print("()");
            }
            for (int space = 1; space <= - 6 * line + 24; space++) {
                System.out.print(" ");
            }
            for (int parenthesis = 1; parenthesis <= 3 * line + 2; parenthesis++) {
                System.out.print("()");
            }
            System.out.println();
        }
    }

    //print the middle part of the heart
    public static void drawLines() {
        for (int line = 1; line <= 4; line++) {
            for (int space = 1; space <= - 1 * line + 5; space++) {
                System.out.print(" ");
            }
            for (int parenthesis = 1; parenthesis <= line + 26; parenthesis++) {
                System.out.print("()");
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int parenthesis = 1; parenthesis <= 30; parenthesis++) {
            System.out.print("()");
        }
        System.out.println();
    }

    // prints the bottom part of the heart
    public static void drawBottom() {
        for (int line = 1; line <= 15; line++) {
            for (int space = 1; space <= 2 * line; space++) {
                System.out.print(" ");
            }
            for (int parenthesis = 1; parenthesis <= -2 * line + 31; parenthesis++) {
                System.out.print("()");
            }
            System.out.println();
        }
    }
}