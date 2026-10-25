package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    private final int waterPerCup = 200;
    private final int milkPerCup = 50;
    private final int coffeeBeansPerCup = 15;

    public void calculateIngredients(int cups) {
        int water = cups * waterPerCup;
        int milk = cups * milkPerCup;
        int coffeeBeans = cups * coffeeBeansPerCup;

        System.out.println("For " + cups + " cups of coffee you will need:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        System.out.println("Write how many cups of coffee you will need:");
        int cups = scanner.nextInt();

        coffeeMachine.calculateIngredients(cups);
    }
}