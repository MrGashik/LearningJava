package lab1;

import lab1.strategy.*;

public class Hero {
    private MoveStrategy moveStrategy;
    private int currentPosition;

    public Hero(MoveStrategy initialMoveStrategy) {
        this.currentPosition = 0;
        this.moveStrategy = initialMoveStrategy;
        System.out.println(this.moveStrategy.heroStatus());
    }

    public void changeStrategyMove(MoveStrategy strategy) {
        this.moveStrategy = strategy;
        System.out.println(this.moveStrategy.heroStatus());
    }

    public void move() {
        this.currentPosition += this.moveStrategy.move();
        System.out.println("The hero overcame " + this.currentPosition + " meters.\n");
    }
}