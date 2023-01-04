// Lefei (Sebastian) Liu
// 01/26/2021
// CSE142
// TA: Emilia Borisova
// Homework 3 (Cafe Wall)
//
// This program will produce an image showing a simplified version of the U.S. national flag

import java.awt.*;
public class Doodle {

    public static void main(String[] args) {
        // create a new drawing panel with white background and a graphic called brush
        DrawingPanel panel = new DrawingPanel(760, 400);
        panel.setBackground(Color.WHITE);
        Graphics brush = panel.getGraphics();

        // draw the components in the national flag
        drawStripes(brush);
        drawRect(brush);
        drawOval(brush);
    }

    // draw the red stripes 
    public static void drawStripes(Graphics brush) {
        brush.setColor(Color.RED);

        for (int i = 1; i <= 7; i++) {
            brush.fillRect(0, 2 * (i - 1) * 31, 760, 31);
        }
    }

    // draw a blue rectangle 
    public static void drawRect(Graphics brush) {
        brush.setColor(Color.BLUE);

        brush.fillRect(0, 0, 304, 217);
    }

    // draw 50 circles 
    public static void drawOval(Graphics brush) {
        brush.setColor(Color.WHITE);

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 6; j++){
                brush.fillOval(13 + 2 * (j - 1) * 25, 11 + 2 * (i - 1) * 22, 25, 25);
            }
        }

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                brush.fillOval(13 + (2 * j - 1) * 25, 11 + (2 * i - 1) * 22, 25, 25);
            }
        }
    }
}