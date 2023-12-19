package lab1.strategy;

public class StrategyRideAHorse implements MoveStrategy {
    @Override
    public int move() {
        return 4;
    }

    @Override
    public String heroStatus() {
        return "подозвал своего коня и оседлал его.";
    }
}
