package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    private final int waterPerCup = 200;
    private final int milkPerCup = 50;
    private final int coffeeBeansPerCup = 15;

    public void checkResources(int water, int milk, int coffeeBeans, int requestedCups) {

        int cupsByWater = water / waterPerCup;
        int cupsByMilk = milk / milkPerCup;
        int cupsByCoffeeBeans = coffeeBeans / coffeeBeansPerCup;

        int possibleCups = Math.min(
                cupsByWater,
                Math.min(cupsByMilk, cupsByCoffeeBeans)
        );

        if (possibleCups == requestedCups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (possibleCups > requestedCups) {
            int extraCups = possibleCups - requestedCups;

            System.out.println(
                    "Yes, I can make that amount of coffee (and even "
                            + extraCups + " more than that)"
            );
        } else {
            System.out.println(
                    "No, I can make only "
                            + possibleCups
                            + " cups of coffee"
            );
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        System.out.println("Write how many ml of water the coffee machine has:");
        int water = scanner.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        int milk = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeans = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        int requestedCups = scanner.nextInt();

        coffeeMachine.checkResources(
                water,
                milk,
                coffeeBeans,
                requestedCups
        );
    }
}