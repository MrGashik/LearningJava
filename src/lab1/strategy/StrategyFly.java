package lab1.strategy;

public class StrategyFly implements MoveStrategy {
    @Override
    public int move() {
        return 5;
    }

    @Override
    public String heroStatus() {
        return "The hero spread his wings and flew.";
    }
}
