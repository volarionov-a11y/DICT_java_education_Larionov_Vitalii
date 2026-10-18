package Hangman;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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

        Set<Character> guessedLetters = new HashSet<>();

        while (attempts > 0 && !new String(hiddenWord).equals(word)) {
            System.out.println();
            System.out.println(new String(hiddenWord));
            System.out.print("Input a letter: ");

            String input = scanner.nextLine();

            // Перевірка: введено рівно один символ
            if (input.length() != 1) {
                System.out.println("You should input a single letter");
                continue;
            }

            char letter = input.charAt(0);

            // Перевірка: тільки мала англійська літера
            if (letter < 'a' || letter > 'z') {
                System.out.println("Please enter a lowercase English letter");
                continue;
            }

            // Перевірка: літера вже вводилась
            if (guessedLetters.contains(letter)) {
                System.out.println("You've already guessed this letter");
                continue;
            }

            guessedLetters.add(letter);

            boolean letterExists = false;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    hiddenWord[i] = letter;
                    letterExists = true;
                }
            }

            if (!letterExists) {
                System.out.println("That letter doesn't appear in the word");
                attempts--;
            }
        }

        if (new String(hiddenWord).equals(word)) {
            System.out.println("You guessed the word " + word + "!");
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}