package lab1;

import lab1.strategy.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("0) Next step");
        System.out.println("1) Stand - 0 step");
        System.out.println("2) Swim - 1 step");
        System.out.println("3) Walk - 2 step");
        System.out.println("4) Pony - 3 step");
        System.out.println("5) Horse - 4 step");
        System.out.println("6) Fly - 5 step");
        System.out.println("7) Stop - end way\n");

        Scanner reader = new Scanner(System.in);
        int mode = 1;
        Hero hero = new Hero(new StrategyStand());

        while (mode != 7) {
            System.out.print("Select the moving mode: ");
            mode = reader.nextInt();

            switch (mode) {
                case 1 -> hero.changeStrategyMove(new StrategyStand());
                case 2 -> hero.changeStrategyMove(new StrategySwim());
                case 3 -> hero.changeStrategyMove(new StrategyWalk());
                case 4 -> hero.changeStrategyMove(new StrategyRideAPony());
                case 5 -> hero.changeStrategyMove(new StrategyRideAHorse());
                case 6 -> hero.changeStrategyMove(new StrategyFly());
                case 7 -> System.out.println("The hero has achieved his goal.");
                default -> System.out.println("The hero continues his movement in the designated mode.");
            }
            if (mode != 7) {
                hero.move();
            }
        }

        reader.close();
    }
}