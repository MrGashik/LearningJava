package lab1.strategy;

public interface MoveStrategy {
    default int move() {
        return 0;
    }

    String heroStatus();
}