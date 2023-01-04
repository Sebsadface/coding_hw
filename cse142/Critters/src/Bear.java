// Lefei (Sebastian) Liu
// 09/03/2021
// CSE142
// TA: Emilia Borisova
// Homework 8 (Critters)
//
// Constructor - defines a bear
// Behavior:
//      Action - always infect if an enemy is in front otherwise hop if possible otherwise turn
//               left.
//      Color - white for a polar bear (when polar is true), black otherwise (when polar is false)
//      String - A lion is displayed alternate on each different move between a slash character (/)
//               and a backslash character (\) starting with a slash.

import java.awt.*;

public class Bear extends Critter {
    private boolean polar;        // Represents whether the bear is a polar bear
    private String currentString; // Represents the current string the bear is displaying

    // Constructs a new bear
    // Parameters:
    //      polar - determine whether the bear is a polar bear
    public Bear (boolean polar) {
        this.polar = polar;
        currentString = "\\";
    }

    // Defines the bear's movement
    // Returns the action of the bear
    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }

    // Defines the bear's color
    // Returns the color of the bear
    public Color getColor() {
        if (polar) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    // Defines the bear's appearance
    // Returns a string which will be the bear's appearance
    public String toString() {
        if (currentString.equals("\\")) {
            currentString = "/";
            return "/";
        } else {
            currentString = "\\";
            return "\\";
        }
    }
}
