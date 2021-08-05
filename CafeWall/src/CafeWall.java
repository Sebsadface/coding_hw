// Lefei (Sebastian) Liu
// 01/26/2021
// CSE142
// TA: Emilia Borisova
// Homework 3 (Cafe Wall)
//
// This program will produce an image demonstrating Café Wall illusion

import java.awt.*;

public class CafeWall {
    // set the size of the gap between each row
    public static final int MORTAR = 2;

    public static void main(String[] args) {
        // create a new drawing panel with gray background and a graphic called brush
        DrawingPanel panel = new DrawingPanel(650, 400);
        panel.setBackground(Color.GRAY);
        Graphics brush = panel.getGraphics();

        // draw all the rows in the image
        drawRow(brush, 0, 0, 4, 20);
        drawRow(brush, 50, 70, 5, 30);

        // draw all the grids in the image
        drawGrid(brush, 10, 150, 4, 25, 0);
        drawGrid(brush, 400, 20, 2, 35, 35);
        drawGrid(brush, 250, 200, 3, 25, 10);
        drawGrid(brush, 425, 180, 5, 20, 10);
    }

    // draw a row with given graphics, position, box pairs, and the size of box
    public static void drawRow(Graphics brush, int x, int y, int boxPairs, int size) {

        for (int i = 1; i <= boxPairs; i++) {
            // set a variable that contains the function needed to determine the horizontal 
            // position of each black box
            int xBlack = 2 * size * (i - 1) + x;

            // draw the black boxes in each row
            brush.setColor(Color.BLACK);
            brush.fillRect(xBlack, y, size, size);

            // draw the blue lines in each black box
            brush.setColor(Color.BLUE);
            brush.drawLine(xBlack, y, xBlack + size, y + size);
            brush.drawLine(xBlack, y + size, xBlack + size, y);

            // draw the white boxes in each row
            brush.setColor(Color.WHITE);
            brush.fillRect(xBlack + size, y, size, size);
        }
    }

    // draw a grid with given graphics, position, row pairs, size and offset
    public static void drawGrid(Graphics brush, int x, int y, int rowPairs, int size, int offset) {

        for (int i = 1; i <= rowPairs; i++) {
            // set two variables that contain the functions needed to determine the vertical 
            // position of each row
            int yGrid1 = y + 2 * (i - 1) * (size + MORTAR);
            int yGrid2 = y + (2 * i - 1) * (size + MORTAR);

            // draw two rows with offset in the second row
            drawRow(brush, x, yGrid1, rowPairs, size);
            drawRow(brush, x + offset, yGrid2, rowPairs, size);
        }
    }
}