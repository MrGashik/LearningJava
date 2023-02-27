package lab1;

import lab1.strategy.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Stand - 0 step");
        System.out.println("Swim - 1 step");
        System.out.println("Walk - 2 step");
        System.out.println("Pony - 3 step");
        System.out.println("Horse - 4 step");
        System.out.println("Fly - 5 step\n");

        Hero hero = new Hero(new StrategyStand());
        hero.move();

        hero.changeStrategyMove(new StrategyWalk());
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