package machine;

abstract class Coffee {
    final private int cost;
    final private int waterNeeded;
    final private int milkNeeded;
    final private int beansNeeded;

    public Coffee(int cost, int waterNeeded, int milkNeeded, int beansNeeded) {
        this.cost = cost;
        this.waterNeeded = waterNeeded;
        this.milkNeeded = milkNeeded;
        this.beansNeeded = beansNeeded;
    }

    public int getCost() {
        return cost;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getMilkNeeded() {
        return milkNeeded;
    }

    public int getBeansNeeded() {
        return beansNeeded;
    }

    public abstract int getPossibleCups(CoffeeMachine cm);

    public String getMissingComponent(CoffeeMachine cm) {
        if (cm.getAvailableWater() / this.getWaterNeeded() == 0) {
            return "Sorry, not enough water!";
        } else if (cm.getAvailableBeans() / this.getBeansNeeded() == 0) {
            return "Sorry, not enough beans!";
        } else if (cm.getAvailableMilk() / this.getMilkNeeded() == 0) {
            return "Sorry, not enough milk.";
        }

        return "";
    }
}

class Expresso extends Coffee {
    public Expresso() {
        super(4, 250, 0, 16);
    }

    public int getPossibleCups(CoffeeMachine cm) {
        int maxWater = cm.getAvailableWater() / this.getWaterNeeded();
        int maxBeans = cm.getAvailableBeans() / this.getBeansNeeded();

        return Math.min(maxWater, maxBeans);
    }
}

class Latte extends Coffee {
    public Latte() {
        super(7, 350, 75, 20);
    }

    public int getPossibleCups(CoffeeMachine cm) {
        int maxWater = cm.getAvailableWater() / this.getWaterNeeded();
        int maxMilk = cm.getAvailableMilk() / this.getMilkNeeded();
        int maxBeans = cm.getAvailableBeans() / this.getBeansNeeded();

        return Math.min(maxWater, Math.min(maxMilk, maxBeans));
    }
}

class Cappuccino extends Coffee {
    public Cappuccino() {
        super(6, 200, 100, 12);
    }

    public int getPossibleCups(CoffeeMachine cm) {
        int maxWater = cm.getAvailableWater() / this.getWaterNeeded();
        int maxMilk = cm.getAvailableMilk() / this.getMilkNeeded();
        int maxBeans = cm.getAvailableBeans() / this.getBeansNeeded();

        return Math.min(maxWater, Math.min(maxMilk, maxBeans));
    }
}