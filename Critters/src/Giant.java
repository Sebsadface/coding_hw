// Lefei (Sebastian) Liu
// 09/03/2021
// CSE142
// TA: Emilia Borisova
// Homework 8 (Critters)
//
// Constructor - defines a giant
// Behavior:
//      Action - always infect if an enemy is in front otherwise hop if possible otherwise turn
//               right.
//      Color - always gray.
//      String - "fee" for 6 moves, then "fie" for 6 moves, then "foe" for 6 moves, then "fum"
//               for 6 moves, then repeat.

import java.awt.*;

public class Giant extends Critter {
    private int stepCount; // Represents the number of steps a giant has moved

    // Constructs a new giant
    public Giant() {
        stepCount = 0;
    }

    // Defines the giant's movement
    // Returns the action of the giant
    public Action getMove(CritterInfo info) {
        stepCount++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }

    // Defines the giant's color
    // Returns the color of the giant
    public Color getColor() {
        return Color.GRAY;
    }

    // Defines the giant's appearance
    // Returns a string which will be the giant's appearance
    public String toString() {
        if (stepCount % 24 < 6) {
            return "fee";
        } else if (stepCount % 24 < 12) {
            return "fie";
        } else if (stepCount % 24 < 18) {
            return "foe";
        } else {
            return "fum";
        }
    }
}
