package lab1;

import lab1.strategy.MoveStrategy;
//import lab1.strategy.StrategyStand;

public class Hero {
    private final String name;
    private MoveStrategy moveStrategy;
    private int currentPosition;

    public Hero(String name, MoveStrategy initialMoveStrategy) {
        this.name = name;
        this.currentPosition = 0;
        this.moveStrategy = initialMoveStrategy;
        //System.out.println(this.moveStrategy.heroStatus());
    }

    public String changeStrategyMove(MoveStrategy strategy) {
        this.moveStrategy = strategy;
        return this.name + " " + this.moveStrategy.heroStatus();
    }

    public String move() {
        this.currentPosition += this.moveStrategy.move();
        return this.name + " преодолел " + this.currentPosition + "м.";
    }

    public void restartMove() {
        this.currentPosition = 0;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Наш герой!";
    }
}