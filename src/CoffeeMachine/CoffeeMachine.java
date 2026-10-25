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
        System.out.println();
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
                "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"
        );

        String choice = scanner.nextLine();

        if (choice.equals("back")) {
            return;
        }

        int requiredWater = 0;
        int requiredMilk = 0;
        int requiredCoffeeBeans = 0;
        int price = 0;

        if (choice.equals("1")) {
            requiredWater = 250;
            requiredMilk = 0;
            requiredCoffeeBeans = 16;
            price = 4;

        } else if (choice.equals("2")) {
            requiredWater = 350;
            requiredMilk = 75;
            requiredCoffeeBeans = 20;
            price = 7;

        } else if (choice.equals("3")) {
            requiredWater = 200;
            requiredMilk = 100;
            requiredCoffeeBeans = 12;
            price = 6;

        } else {
            return;
        }

        if (water < requiredWater) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < requiredMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffeeBeans < requiredCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");

            water -= requiredWater;
            milk -= requiredMilk;
            coffeeBeans -= requiredCoffeeBeans;
            cups--;
            money += price;
        }
    }

    public void fill(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add:");
        water += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many ml of milk do you want to add:");
        milk += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += Integer.parseInt(scanner.nextLine());
    }

    public void take() {
        System.out.println("I gave you " + money);
        money = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();

            if (action.equals("buy")) {
                buy(scanner);

            } else if (action.equals("fill")) {
                fill(scanner);

            } else if (action.equals("take")) {
                take();

            } else if (action.equals("remaining")) {
                remaining();

            } else if (action.equals("exit")) {
                break;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();
    }
}