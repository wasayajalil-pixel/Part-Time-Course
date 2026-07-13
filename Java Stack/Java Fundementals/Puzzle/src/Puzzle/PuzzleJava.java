package Puzzle;

import java.util.ArrayList;
import java.util.Random;

public class PuzzleJava {

    // Random object used by all methods
    private Random rand = new Random();

    // ---------------------------------------------------
    // 1. Generate 10 random numbers between 1 and 20
    // ---------------------------------------------------
    public ArrayList<Integer> getTenRolls() {

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(rand.nextInt(20) + 1);
        }

        return numbers;
    }

    // ---------------------------------------------------
    // 2. Return one random letter
    // ---------------------------------------------------
    public char getRandomLetter() {

        char[] alphabet = {
                'a','b','c','d','e','f','g','h','i','j',
                'k','l','m','n','o','p','q','r','s','t',
                'u','v','w','x','y','z'
        };

        int randomIndex = rand.nextInt(26);

        return alphabet[randomIndex];
    }

    // ---------------------------------------------------
    // 3. Generate an 8-character password
    // ---------------------------------------------------
    public String generatePassword() {

        String password = "";

        for (int i = 0; i < 8; i++) {
            password += getRandomLetter();
        }

        return password;
    }

    // ---------------------------------------------------
    // 4. Generate an array of passwords
    // ---------------------------------------------------
    public String[] getNewPasswordSet(int length) {

        String[] passwords = new String[length];

        for (int i = 0; i < length; i++) {
            passwords[i] = generatePassword();
        }

        return passwords;
    }

    // ---------------------------------------------------
    // BONUS: Shuffle an ArrayList
    // ---------------------------------------------------
    public void shuffleArray(ArrayList<Integer> numbers) {

        for (int i = 0; i < numbers.size(); i++) {

            int randomIndex = rand.nextInt(numbers.size());

            // Swap values
            int temp = numbers.get(i);
            numbers.set(i, numbers.get(randomIndex));
            numbers.set(randomIndex, temp);
        }
    }
}
