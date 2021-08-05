// Lefei (Sebastian) Liu
// 1/12/2021
// CSE142
// TA: Emilia Borisova
// Homework 1 (Song)
//
// This program will output a cumulative song called "There was an Old Woman Who Swallowed a Fly".

public class Song {

    public static void main(String [] args) {
        verse1();
        verse2();
        verse3();
        verse4();
        verse5();
        customVerse();
        verse7();
    }


    //Prints the lyrics in the first verse and a blank line.
    public static void verse1() {
        System.out.println ("There was an old woman who swallowed a fly.");
        fly();
        System.out.println();
    }

    //Prints the lyrics in the second verse and a blank line.
    public static void verse2() {
        System.out.println("There was an old woman who swallowed a spider,");
        System.out.println("That wriggled and iggled and jiggled inside her.");
        spider();
        System.out.println();
    }

    //Prints the lyrics in the third verse and a blank line.
    public static void verse3() {
        System.out.println("There was an old woman who swallowed a bird,");
        System.out.println("How absurd to swallow a bird.");
        bird();
        System.out.println();
    }

    //Prints the lyrics in the fourth verse and a blank line.
    public static void verse4() {
        System.out.println("There was an old woman who swallowed a cat,");
        System.out.println("Imagine that to swallow a cat.");
        cat();
        System.out.println();
    }

    //Prints the lyrics in the fifth verse and a blank line.
    public static void verse5() {
        System.out.println("There was an old woman who swallowed a dog,");
        System.out.println("What a hog to swallow a dog.");
        dog();
        System.out.println();
    }

    //Prints the lyrics in my custom sixth verse and a blank line.
    public static void customVerse() {
        System.out.println("There was an old woman who swallowed a shark,");
        System.out.println("It's dark to swallow a shark.");
        shark();
        System.out.println();
    }

    //Prints the lyrics in the seventh verse.
    public static void verse7() {
        System.out.println("There was an old woman who swallowed a horse,");
        System.out.println("She died of course.");
    }


    //Prints the repeating lyrics about fly.
    public static void fly() {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.");
    }

    //Prints the repeating lyrics about spider and fly.
    public static void spider() {
        System.out.println("She swallowed the spider to catch the fly,");
        fly();
    }

    //Prints the repeating lyrics about bird, spider and fly.
    public static void bird() {
        System.out.println("She swallowed the bird to catch the spider,");
        spider();
    }

    //Prints the repeating lyrics about cat, bird, spider and fly.
    public static void cat() {
        System.out.println("She swallowed the cat to catch the bird,");
        bird();
    }

    //Prints the repeating lyrics about dog, cat, bird, spider and fly.
    public static void dog() {
        System.out.println("She swallowed the dog to catch the cat,");
        cat();
    }

    //Prints the repeating lyrics about dog, cat, bird, spider,and fly,
    //and lyrics about shark in my custom verse.
    public static void shark() {
        System.out.println("She swallowed the shark to catch the dog,");
        dog();
    }
}