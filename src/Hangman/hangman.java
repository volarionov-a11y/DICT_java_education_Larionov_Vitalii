package Hangman;

import java.util.Random;
import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("HANGMAN");

        String[] words = {"python", "java", "javascript", "kotlin"};
        String word = words[random.nextInt(words.length)];

        String hint = word.substring(0, 2);

        for (int i = 2; i < word.length(); i++) {
            hint += "-";
        }

        System.out.print("Guess the word " + hint + ": ");
        String answer = scanner.nextLine();

        if (answer.equals(word)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}