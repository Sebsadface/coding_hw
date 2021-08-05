// Lefei (Sebastian) Liu
// 02/23/2021
// CSE142
// TA: Emilia Borisova
// Homework 6 (Baby Names)
//
// This program allows you to search through the data from the Social Security Administration to
// see how popular a particular name has been since 1880 or 1920.

import java.util.*;
import java.io.*;
import java.awt.*;

public class Names {
    public static final int DECADES = 14;
    public static final int START_YEAR = 1880;
    public static final int WIDTH = 70;

    public static void main (String[] args) throws FileNotFoundException {
       Scanner console = new Scanner(System.in);
       Scanner input = new Scanner(new File("names.txt"));

        intro();

        String name = getName(console);
        String sex = getSex(console);
        String line = findName(input, name, sex);

        if (!line.equals("")) {
            drawGraph(line);
        }
    }

    // Prints a introduction of the program
    // Does not return any value
    public static void intro() {
        System.out.println("This program allows you to search through the");
        System.out.println("data from the Social Security Administration");
        System.out.println("to see how popular a particular name has been");
        System.out.println("since " + START_YEAR + ".");
        System.out.println();
    }

    // Reads in an user input about the name they want to search for
    // Returns the name user typed
    // Parameters:
    //    Scanner console - the scanner for user input
    public static String getName(Scanner console) {
        System.out.print("name? ");
        return console.nextLine();
    }

    // Reads in an user input about the sex of the name they want to search for
    // Returns the sex user typed
    // Parameters:
    //    Scanner console - the scanner for user input
    public static String getSex(Scanner console) {
        System.out.print("sex (M or F)? ");
        return console.nextLine();

    }

    // Reads through the file and find matching name and sex combination
    // Returns the matching line
    // Parameters:
    //    Scanner console - the scanner for user input
    //    String name - the name the user wants to search for
    //    String sex - the sex of the name the user wants to search for
    public static String findName(Scanner input, String name, String sex) {
        String line;
        while (input.hasNextLine()) {
            line = input.nextLine();
            Scanner lineReader = new Scanner(line);
            if (lineReader.next().equalsIgnoreCase(name) && lineReader.next().equalsIgnoreCase(sex)) {
                return line;
            }
        }
        System.out.println("name/sex combination not found");
        return "";
    }

    // Draws the graph with popularity information from the matching line
    // Does not return any value
    // Parameters:
    //    String line - the matching line of information
    public static void drawGraph(String line) {
        Scanner lineReader = new Scanner (line);
        DrawingPanel panel = new DrawingPanel(WIDTH * DECADES, 550);
        Graphics brush = panel.getGraphics();

        drawGrid(brush);
        drawData(lineReader, brush);
    }

    // Draws the grid in the graph
    // Does not return any value
    // Parameters:
    //    Graphics brush - the graphics used to draw grid
    public static void drawGrid(Graphics brush) {
        brush.drawLine(0, 25, 980, 25);
        brush.drawLine(0, 525, 980, 525);
        for (int i = 0; i < DECADES; i++) {
            brush.drawLine(WIDTH * i, 0, WIDTH * i, 550);
            brush.drawString(String.valueOf(START_YEAR + i * 10), WIDTH * i, 550 );
        }
    }

    // Draws the graph using the data
    // Does not return any value
    // Parameters:
    //    Scanner lineReader - the scanner used to read the line
    //    Graphics brush - the graphics used to draw graph
    public static void drawData(Scanner lineReader, Graphics brush) {
        String name = lineReader.next();
        String sex = lineReader.next();
        int rank = lineReader.nextInt();
        int rank2;
        brush.setColor(Color.RED);
        brush.drawString(name + " "  + sex + " " + rank,0, getY(rank));
        for (int i = 0; i < DECADES - 1; i++) {
            rank2 = lineReader.nextInt();
            brush.drawLine(WIDTH * i, getY(rank), WIDTH * (i + 1), getY(rank2));
            brush.drawString(name + " "  + sex + " " + rank2,WIDTH * (i + 1), getY(rank2));
            rank = rank2;
        }
    }

    // Calculates the Y coordinate of a given name rank
    // Returns the Y coordinate
    // Parameters:
    //    int rank - the rank of a name of a certain year
    public static int getY(int rank) {
        if (rank == 0) {
            return 525;
        } else {
            return 25 + (rank - 1) / 2;
        }
    }
}
