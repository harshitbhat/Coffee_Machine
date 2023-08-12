package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private int availableWater;
    private int availableMilk;
    private int availableBeans;
    private int availableCash;
    private int disposableCups;

    public CoffeeMachine(int availableWater, int availableMilk, int availableBeans, int availableCash, int disposableCups) {
        this.availableWater = availableWater;
        this.availableMilk = availableMilk;
        this.availableBeans = availableBeans;
        this.availableCash = availableCash;
        this.disposableCups = disposableCups;
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setDisposableCups(int disposableCups) {
        this.disposableCups = disposableCups;
    }

    public int getAvailableWater() {
        return availableWater;
    }

    public void setAvailableWater(int availableWater) {
        this.availableWater = availableWater;
    }

    public int getAvailableMilk() {
        return availableMilk;
    }

    public void setAvailableMilk(int availableMilk) {
        this.availableMilk = availableMilk;
    }

    public int getAvailableBeans() {
        return availableBeans;
    }

    public void setAvailableBeans(int availableBeans) {
        this.availableBeans = availableBeans;
    }

    public int getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(int availableCash) {
        this.availableCash = availableCash;
    }

    public void printCoffeeMachineState() {
        String state = String.format("\nThe coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money\n ",
                this.getAvailableWater(), this.getAvailableMilk(),
                this.getAvailableBeans(), this.getDisposableCups(), this.getAvailableCash()
        );

        System.out.println(state);
    }

    public void fillMachine(Scanner sc) {
        System.out.println("Write how many ml of water you want to add:");
        int water = sc.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int milk = sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int beans = sc.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        int cups = sc.nextInt();

        this.setAvailableWater(this.getAvailableWater() + water);
        this.setAvailableMilk(this.getAvailableMilk() + milk);
        this.setAvailableBeans(this.getAvailableBeans() + beans);
        this.setDisposableCups(this.getDisposableCups() + cups);
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d\n", this.getAvailableCash());
        this.setAvailableCash(0);
    }

    public void chooseCoffee(Scanner sc) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        String choice = sc.next();

        Coffee c = null;

        switch (choice) {
            case "1":
                c = new Expresso();
                break;
            case "2":
                c = new Latte();
                break;
            case "3":
                c = new Cappuccino();
                break;
            default:
                break;
        }

        if (c != null) {
            int possibleExs = c.getPossibleCups(this);
            if (possibleExs > 0) {
                this.setAvailableCash(this.getAvailableCash() + c.getCost());
                this.setAvailableBeans(this.getAvailableBeans() - c.getBeansNeeded());
                this.setAvailableMilk(this.getAvailableMilk() - c.getMilkNeeded());
                this.setAvailableWater(this.getAvailableWater() - c.getWaterNeeded());
                this.setDisposableCups(this.getDisposableCups() - 1);

                System.out.println("I have enough resources, making you a coffee!\n");
            } else {
                String s = c.getMissingComponent(this);
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 550, 9);

//        coffeeMachine.printCoffeeMachineState();

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();

            if (action.equals("buy")) {
                coffeeMachine.chooseCoffee(scanner);
            } else if (action.equals("fill")) {
                coffeeMachine.fillMachine(scanner);
            } else if (action.equals("take")) {
                coffeeMachine.takeMoney();
            } else if (action.equals("remaining")) {
                coffeeMachine.printCoffeeMachineState();
            } else if (action.equals("exit")) {
                break;
            }
        }

    }
}