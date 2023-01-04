// Sebastian Liu

import java.awt.*;

public class Star extends Critter {
    private int stepCount;

    public Star() {
        stepCount = 0;
    }

    public Action getMove(CritterInfo info) {
        stepCount++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL) {
            if (info.getRight() == Neighbor.WALL || info.getLeft() == Neighbor.OTHER) {
                return Action.LEFT;
            } else {
                return Action.RIGHT;
            }
        } else if (info.getRight() == Neighbor.OTHER) {
            return Action.RIGHT;
        } else if (info.getLeft() == Neighbor.OTHER) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME || info.getRight() == Neighbor.SAME ||
                info.getBack() == Neighbor.SAME || info.getLeft() == Neighbor.SAME) {
            if (info.getFront() == Neighbor.OTHER) {
                return Action.INFECT;
            } else if (info.getRight() == Neighbor.OTHER) {
                return Action.RIGHT;
            } else if (info.getLeft() == Neighbor.OTHER) {
                return Action.LEFT;
            } else if (info.leftThreat() || info.rightThreat()) {
                return Action.HOP;
            } else {
                return Action.RIGHT;
            }
        } else {
            return Action.HOP;
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
