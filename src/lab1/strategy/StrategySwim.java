package lab1.strategy;

public class StrategySwim implements MoveStrategy {
    @Override
    public int move() {
        return 1;
    }

    @Override
    public String heroStatus() {
        return "видит цель и не видит препятствий... Хорошо, что на берегу была лодка.";
    }
}
