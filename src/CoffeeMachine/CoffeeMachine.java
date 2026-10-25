package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.cups = 9;
        this.money = 550;
    }

    public void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
        System.out.println();
    }

    public void buy(Scanner scanner) {
        System.out.println(
                "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:"
        );

        int choice = scanner.nextInt();

        if (choice == 1) {
            water -= 250;
            coffeeBeans -= 16;
            cups--;
            money += 4;

        } else if (choice == 2) {
            water -= 350;
            milk -= 75;
            coffeeBeans -= 20;
            cups--;
            money += 7;

        } else if (choice == 3) {
            water -= 200;
            milk -= 100;
            coffeeBeans -= 12;
            cups--;
            money += 6;
        }
    }

    public void fill(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable coffee cups you want to add:");
        cups += scanner.nextInt();
    }

    public void take() {
        System.out.println("I gave you " + money);
        money = 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.remaining();

        System.out.println("Write action (buy, fill, take):");
        String action = scanner.nextLine();

        if (action.equals("buy")) {
            coffeeMachine.buy(scanner);

        } else if (action.equals("fill")) {
            coffeeMachine.fill(scanner);

        } else if (action.equals("take")) {
            coffeeMachine.take();
        }

        System.out.println();
        coffeeMachine.remaining();
    }
}