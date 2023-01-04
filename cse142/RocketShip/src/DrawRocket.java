// Lefei (Sebastian) Liu
// 19/1/2021
// CSE142
// TA: Emilia Borisova
// Homework 2 (Rocket Ship)
//
// This program will output a scalable ASCII art figure which looks like a rocket ship

public class DrawRocket {
    // sets the size of the Rocket Ship
    public static final int SIZE = 6;

    public static void main(String[] args) {
        headOrEngine();
        cutOff();
        levelTypeOne();
        levelTypeTwo();
        cutOff();
        levelTypeTwo();
        levelTypeOne();
        cutOff();
        headOrEngine();
    }

    // prints the head or engine of the rocket ship
    public static void headOrEngine() {
        for (int i = 0; i < 2 * SIZE - 1; i++) {
            for (int k = 2 * SIZE - 1; k > i; k--) {
                System.out.print(" ");
            }
            for (int l = 0; l < i + 1 ; l++) {
                System.out.print("/");
            }
            System.out.print("**");
            for (int l = 0; l < i + 1; l++) {
                System.out.print("\\");
            }
            System.out.println();
        }
    }

    // prints the cutoff between each part of the rocket ship
    public static void cutOff() {
        System.out.print("+");
        for (int i = 0; i < 2 * SIZE; i++) {
            System.out.print("=*");
        }
        System.out.println("+");
    }

    // prints the first type of the rocket ship level
    public static void levelTypeOne() {
        for (int l = 0; l < SIZE; l++) {
            System.out.print("|");
            for (int i = SIZE - 1; i > l; i--) {
                System.out.print(".");
            }
            for (int j = 0; j < l + 1; j++) {
                System.out.print("/\\");
            }
            for (int k = 2 * SIZE - 2; k > 2 * l; k--) {
                System.out.print(".");
            }
            for (int j = 0; j < l + 1; j++) {
                System.out.print("/\\");
            }
            for (int i = SIZE - 1; i > l; i--) {
                System.out.print(".");
            }
            System.out.println("|");
        }
    }

    // prints the second type of the rocket ship level
    public static void levelTypeTwo() {
        for (int l = 0; l < SIZE; l++) {
            System.out.print("|");
            for (int i = 0; i < l; i++) {
                System.out.print(".");
            }
            for (int j = SIZE; j > l; j--) {
                System.out.print("\\/");
            }
            for (int k = 0; k < 2 * l; k++) {
                System.out.print(".");
            }
            for (int j = SIZE; j > l; j--) {
                System.out.print("\\/");
            }
            for (int i = 0; i < l; i++) {
                System.out.print(".");
            }
            System.out.println("|");
        }
    }
}
