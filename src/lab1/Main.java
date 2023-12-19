package lab1;

import lab1.strategy.*;

import java.util.Scanner;

public class Main {
    static String[] conditions = {
            "Next step",
            "Stand - 0 step",
            "Boat - 1 step",
            "Walk - 2 step",
            "Pony - 3 step",
            "Horse - 4 step",
            "Fly - 5 step",
            "Stop - end way",
    };
    public static void main(String[] args) {

        for (int i = 0; i < conditions.length; i++) {
            System.out.println(i + ": " + conditions[i]);
        }

        Scanner reader = new Scanner(System.in);
        int mode = 1;
        int prev = mode;
        StringBuilder str = new StringBuilder();
        Hero hero = new Hero("Alex", new StrategyStand());

        while (mode != 7) {
            System.out.print("Select the moving mode: ");
            if (reader.hasNextInt()) {
                mode = reader.nextInt();
                if (mode == prev) {
                    System.out.println("The hero continues his movement in the designated mode.");
                } else {
                    switch (mode) {
                        case 1 -> {
                            hero.changeStrategyMove(new StrategyStand());
                            prev = mode;
                        }
                        case 2 -> {
                            hero.changeStrategyMove(new StrategySwim());
                            prev = mode;
                        }
                        case 3 -> {
                            hero.changeStrategyMove(new StrategyWalk());
                            prev = mode;
                        }
                        case 4 -> {
                            hero.changeStrategyMove(new StrategyRideAPony());
                            prev = mode;
                        }
                        case 5 -> {
                            hero.changeStrategyMove(new StrategyRideAHorse());
                            prev = mode;
                        }
                        case 6 -> {
                            hero.changeStrategyMove(new StrategyFly());
                            prev = mode;
                        }
                        case 7 -> System.out.println("The hero has achieved his goal.");
                        default -> System.out.println("The hero continues his movement in the designated mode.");
                    }
                }
                if (mode != 7) {
                    hero.move();
                }
            } else {
                str.append(reader.next());
                System.out.println("ERROR: You have written \"" + str + "\", but you need to enter the number!\n");
                str.delete(0, str.length());
            }
        }

        reader.close();
    }
}