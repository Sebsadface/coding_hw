// Lefei (Sebastian) Liu
// 09/03/2021
// CSE142
// TA: Emilia Borisova
// Homework 8 (Critters)
//
// Constructor - defines a lion
// Behavior:
//      Action - always infect if an enemy is in front otherwise if a wall is in front or to the
//               right, then turn left otherwise if a fellow Lion is in front, then turn right
//               otherwise hop.
//      Color - A lion randomly picks one of three colors (red, green, and blue) and uses that
//              color for three moves then randomly picks again.
//      String - A lion is displayed "L"

import java.awt.*;
import java.util.*;

public class Lion extends Critter {
    private int stepCount; // Represents the number of steps a lion has moved
    private int color;     // Represents the number of the color a lion going to change to
    private Random r; // Represents the random color of a lion


    // Constructs a new lion
    public Lion() {
        stepCount = 0;
        r = new Random();
        color = r.nextInt(3);
    }

    // Defines the lion's movement
    // Returns the action of the lion
    public Action getMove(CritterInfo info) {
        stepCount++;

        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }

    // Defines the lion's color
    // Returns the color of the lion
    public Color getColor() {
        if (stepCount != 0 && stepCount % 3 == 0) {
            color = r.nextInt(3);
        }
            if (color == 0) {
                return Color.RED;
            } else if (color == 1) {
                return Color.GREEN;
            } else {
                return Color.BLUE;
            }
    }

    // Defines the lion's appearance
    // Returns a string which will be the lion's appearance
    public String toString () {
        return "L";
    }
}
