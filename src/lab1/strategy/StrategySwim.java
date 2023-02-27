package lab1.strategy;

public class StrategySwim implements MoveStrategy {
    @Override
    public int move() {
        return 1;
    }

    @Override
    public String heroStatus() {
        return "The hero decided that it would be easier to swim on the ground... Well, he knows best.";
    }
}
