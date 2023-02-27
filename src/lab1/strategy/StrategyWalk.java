package lab1.strategy;

public class StrategyWalk implements MoveStrategy {
    @Override
    public int move() {
        return 2;
    }
    @Override
    public String heroStatus() {
        return "The hero walks.";
    }
}
