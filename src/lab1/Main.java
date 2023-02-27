package lab1;

import lab1.strategy.*;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero(new StrategyWalk());
        hero.move();

        hero.changeStrategyMove(new StrategyFly());
        hero.move();

        hero.changeStrategyMove(new StrategyRideAHorse());
        hero.move();

        hero.changeStrategyMove(new StrategySwim());
        hero.move();

        hero.changeStrategyMove(new StrategyRideAPony());
        hero.move();
    }
}