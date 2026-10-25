package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;

    private State state;
    private boolean running;

    private enum State {
        CHOOSING_ACTION,
        CHOOSING_COFFEE,
        FILLING_WATER,
        FILLING_MILK,
        FILLING_COFFEE,
        FILLING_CUPS
    }

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.cups = 9;
        this.money = 550;

        this.state = State.CHOOSING_ACTION;
        this.running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void printPrompt() {
        switch (state) {
            case CHOOSING_ACTION:
                System.out.println(
                        "Write action (buy, fill, take, remaining, exit):"
                );
                break;

            case CHOOSING_COFFEE:
                System.out.println(
                        "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"
                );
                break;

            case FILLING_WATER:
                System.out.println(
                        "Write how many ml of water do you want to add:"
                );
                break;

            case FILLING_MILK:
                System.out.println(
                        "Write how many ml of milk do you want to add:"
                );
                break;

            case FILLING_COFFEE:
                System.out.println(
                        "Write how many grams of coffee beans do you want to add:"
                );
                break;

            case FILLING_CUPS:
                System.out.println(
                        "Write how many disposable cups of coffee do you want to add:"
                );
                break;
        }
    }

    public void processInput(String input) {

        switch (state) {

            case CHOOSING_ACTION:
                processAction(input);
                break;

            case CHOOSING_COFFEE:
                processCoffeeChoice(input);
                break;

            case FILLING_WATER:
                water += Integer.parseInt(input);
                state = State.FILLING_MILK;
                break;

            case FILLING_MILK:
                milk += Integer.parseInt(input);
                state = State.FILLING_COFFEE;
                break;

            case FILLING_COFFEE:
                coffeeBeans += Integer.parseInt(input);
                state = State.FILLING_CUPS;
                break;

            case FILLING_CUPS:
                cups += Integer.parseInt(input);
                state = State.CHOOSING_ACTION;
                break;
        }
    }

    private void processAction(String input) {

        switch (input) {

            case "buy":
                state = State.CHOOSING_COFFEE;
                break;

            case "fill":
                state = State.FILLING_WATER;
                break;

            case "take":
                take();
                break;

            case "remaining":
                remaining();
                break;

            case "exit":
                running = false;
                break;
        }
    }

    private void processCoffeeChoice(String input) {

        if (input.equals("back")) {
            state = State.CHOOSING_ACTION;
            return;
        }

        int requiredWater = 0;
        int requiredMilk = 0;
        int requiredCoffeeBeans = 0;
        int price = 0;

        switch (input) {

            case "1":
                requiredWater = 250;
                requiredCoffeeBeans = 16;
                price = 4;
                break;

            case "2":
                requiredWater = 350;
                requiredMilk = 75;
                requiredCoffeeBeans = 20;
                price = 7;
                break;

            case "3":
                requiredWater = 200;
                requiredMilk = 100;
                requiredCoffeeBeans = 12;
                price = 6;
                break;

            default:
                state = State.CHOOSING_ACTION;
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
            System.out.println(
                    "I have enough resources, making you a coffee!"
            );

            water -= requiredWater;
            milk -= requiredMilk;
            coffeeBeans -= requiredCoffeeBeans;
            cups--;
            money += price;
        }

        state = State.CHOOSING_ACTION;
    }

    private void take() {
        System.out.println("I gave you " + money);
        money = 0;
    }

    private void remaining() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (coffeeMachine.isRunning()) {

            coffeeMachine.printPrompt();

            String input = scanner.nextLine();

            coffeeMachine.processInput(input);

            if (coffeeMachine.isRunning()) {
                System.out.println();
            }
        }
    }
}