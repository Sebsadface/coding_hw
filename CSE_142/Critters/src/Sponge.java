import java.awt.*;

public class Sponge extends Critter {
    private boolean infect;
    private boolean turn;
    private int turnCount;
    private String str;

    public Sponge() {
        infect = false;
        turn = false;
        turnCount = 0;
        str = "-";
    }

    public Critter.Action getMove(CritterInfo info) {
        infect = false;
        turn = false;
        if (info.getFront() == Neighbor.OTHER) {
            infect = true;
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else if (turnCount % 3 == 0) {
            turnCount++;
            turn = true;
            return Action.LEFT;
        } else {
            turnCount++;
            turn = true;
            return Action.RIGHT;
        }
    }

    public Color getColor() {
        return Color.YELLOW;
    }

    public String toString() {
        if (infect) {
            str = str + "-";
        }
        if (turn && str.length() > 1) {
            str = str.substring(0, str.length() - 1);
        }
        return "[" + str + "]";
    }
}
