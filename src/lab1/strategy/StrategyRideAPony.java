package lab1.strategy;

public class StrategyRideAPony implements MoveStrategy {
    @Override
    public int move() {
        return 3;
    }

    @Override
    public String heroStatus() {
        return "The hero called his horse, but his pony came running, and he had to saddle him.";
    }
}
