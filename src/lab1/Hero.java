package lab1;

import lab1.strategy.*;
public class Hero {
    private MoveStrategy moveStrategy;
    private int currentPosition;

    {
        currentPosition = 0;
        moveStrategy = new StrategyWalk();
        System.out.println(moveStrategy.heroStatus());
    }

    public void changeStrategyMove(MoveStrategy strategy) {
        moveStrategy = strategy;
        System.out.println(moveStrategy.heroStatus());
    }

    public void move() {
        currentPosition += moveStrategy.move();
        System.out.println("The hero is at " + currentPosition + " points.\n");
    }
}