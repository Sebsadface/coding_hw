// Lefei (Sebastian) Liu
// 04/22/2021
// CSE143
// TA: Randair Porter
// Homework 3 (AssassinManager)
//
// Class AssassinManager can be used to manage a game of assassin keeping track of who is stalking
// whom and the history of who killed whom

import java.util.*;

public class AssassinManager {
    private AssassinNode killRing; // a linked list keeps track of who is stalking whom
    private AssassinNode graveyard; // a linked list keeps track of who is killed by whom

    // pre: List<String> names is not empty (throws IllegalArgumentException if not)
    // post: constructs an assassin manager with given names of the player
    //       fills the kill ring with given list of names in the same order in which they
    //       arrpear in the list
    public AssassinManager(List<String> names) {
        checkArgument(names);
        for (int i = names.size() - 1; i >= 0; i--) {
            killRing = new AssassinNode(names.get(i), killRing);
        }
    }

    // post: prints the names of the people in the kill ring
    public void printKillRing() {
        AssassinNode current = killRing;
        while (current.next != null) {
            System.out.println("    " + current.name + " is stalking " + current.next.name);
            current = current.next;
        }
        System.out.println("    " + current.name + " is stalking " + killRing.name);
    }

    // post: print the names of the people that have been assassinated and the names of the killers
    public void printGraveyard() {
        if (graveyard != null) {
            AssassinNode current = graveyard;
            while (current != null) {
                System.out.println("    " + current.name + " was killed by " + current.killer);
                current = current.next;
            }
        }
    }

    // post: returns true if the given name is in the current kill ring
    //       returns false otherwise
    public boolean killRingContains(String name) {
        AssassinNode current = killRing;
        return contains(current, name);
    }

    // post: returns true if the given name is in the current graveyard
    //       returns false otherwise
    public boolean graveyardContains(String name) {
        AssassinNode current = graveyard;
        return contains(current, name);
    }

    // post: returns true if the current kill ring contains just one person
    //       returns false otherwise
    public boolean gameOver() {
        return (killRing.next == null);
    }

    // post: returns the name of the winner of the game (the last person in the kill ring)
    //       returns null if the game is not over
    public String winner() {
        if (gameOver()) {
            return killRing.name;
        } else {
            return null;
        }
    }

    // pre: String name is in the current kill ring (throws IllegalArgumentException if not)
    //      the current kill ring contains more than one name (throws IllegalStateException if not)
    // post: records the killing of the person with the given name and transfers the person from
    //       the kill ring to the graveyard
    public void kill(String name) {
        checkState();
        checkArgument(name);
        AssassinNode graveyardLast = graveyard;
        if (killRing.name.equalsIgnoreCase(name)) {
            graveyard = killRing;
            graveyard.killer = getTheLastName(killRing);
            killRing = killRing.next;
        } else {
            AssassinNode current = killRing;
            while (!current.next.name.equalsIgnoreCase(name)) {
                current = current.next;
            }
            graveyard = current.next;
            graveyard.killer = current.name;
            current.next = current.next.next;
        }
        graveyard.next = graveyardLast;
    }

    // post: checks whether given list of names is empty
    //       throws IllegalArgumentException if the list is empty
    private void checkArgument(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("names: " + names);
        }
    }

    // post: check whether the current kill ring contains the given name
    //       throws IllegalArgumentException if not
    private void checkArgument(String name) {
        if (! killRingContains(name)) {
            throw new IllegalArgumentException("name: " + name);
        }
    }

    // post: check whether the game is over
    //       throws IllegalStateException if game is over
    private void checkState() {
        if (gameOver()) {
            throw new IllegalStateException("game is over");
        }
    }

    // post: returns true if the given name is in the given linked list
    //       returns false otherwise
    private boolean contains(AssassinNode current, String name) {
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // post: returns the last name in the current kill ring
    private String getTheLastName(AssassinNode killRing) {
        AssassinNode current = killRing;
        while (current.next != null) {
            current = current.next;
        }
        return current.name;
    }
}
