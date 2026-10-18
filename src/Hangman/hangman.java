package Hangman;

import java.util.Random;
import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("HANGMAN");
        System.out.println();

        String[] words = {"python", "java", "javascript", "kotlin"};
        String word = words[random.nextInt(words.length)];

        char[] hiddenWord = new char[word.length()];

        for (int i = 0; i < hiddenWord.length; i++) {
            hiddenWord[i] = '-';
        }

        int attempts = 8;

        while (attempts > 0 && !new String(hiddenWord).equals(word)) {
            System.out.println();
            System.out.println(new String(hiddenWord));
            System.out.print("Input a letter: ");

            String input = scanner.nextLine();
            char letter = input.charAt(0);

            boolean letterExists = false;
            boolean improved = false;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    letterExists = true;

                    if (hiddenWord[i] == '-') {
                        hiddenWord[i] = letter;
                        improved = true;
                    }
                }
            }

            if (!letterExists) {
                System.out.println("That letter doesn't appear in the word");
                attempts--;
            } else if (!improved) {
                System.out.println("No improvements");
                attempts--;
            }
        }

        if (new String(hiddenWord).equals(word)) {
            System.out.println();
            System.out.println("You guessed the word!");
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}