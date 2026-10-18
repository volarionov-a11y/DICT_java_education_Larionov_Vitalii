package Hangman;

import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("HANGMAN");

        String word = "java";

        System.out.print("Guess the word: ");
        String answer = scanner.nextLine();

        if (answer.equals(word)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}