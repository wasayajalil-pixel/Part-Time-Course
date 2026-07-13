package Puzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class TestPuzzleJava {

    public static void main(String[] args) {

        PuzzleJava generator = new PuzzleJava();

        // Test getTenRolls
        ArrayList<Integer> rolls = generator.getTenRolls();
        System.out.println("10 Random Rolls:");
        System.out.println(rolls);

        // Test getRandomLetter
        System.out.println("\nRandom Letter:");
        System.out.println(generator.getRandomLetter());

        // Test generatePassword
        System.out.println("\nRandom Password:");
        System.out.println(generator.generatePassword());

        // Test getNewPasswordSet
        System.out.println("\nPassword Set:");
        String[] passwords = generator.getNewPasswordSet(5);
        System.out.println(Arrays.toString(passwords));

        // Test shuffleArray
        System.out.println("\nBefore Shuffle:");
        System.out.println(rolls);

        generator.shuffleArray(rolls);

        System.out.println("After Shuffle:");
        System.out.println(rolls);
    }
}