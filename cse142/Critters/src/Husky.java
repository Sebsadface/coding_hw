// Lefei (Sebastian) Liu
// 09/03/2021
// CSE142
// TA: Emilia Borisova
// Homework 8 (Critters)
//
// Constructor - defines a husky

import java.awt.*;

public class Husky extends Critter {
    private int stepCount;

    public Husky() {
        stepCount = 0;
    }

    public Action getMove(CritterInfo info) {
        stepCount++;
            if (info.getFront() == Neighbor.OTHER) {
                return Action.INFECT;
            }  else if (info.getRight() == Neighbor.OTHER) {
                return Action.RIGHT;
            } else if (info.getLeft() == Neighbor.OTHER) {
                return Action.LEFT;
            } else if (info.leftThreat() || info.rightThreat()) {
                return Action.HOP;
            } else {
                return Action.RIGHT;
            }

    }

    public Color getColor() {
        if (stepCount % 3 == 0) {
            return Color.RED;
        } else if ((stepCount - 1) % 3 == 0) {
            return Color.GREEN;
        } else {
            return Color.BLUE;
        }
    }

    public String toString() {
        if (stepCount % 3 == 0) {
            return ".";
        } else if ((stepCount - 1) % 3 == 0) {
            return "`";
        } else {
            return "'";
        }
    }
}
