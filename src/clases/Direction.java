package clases;

public enum Direction {
    UP(1),
    RIGHT(2),
    DOWN(3),
    LEFT(4);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static Direction getDirection(int value) {
        Direction dir = null;

        if (value == 1) dir = Direction.UP;
        if (value == 2) dir = Direction.RIGHT;
        if (value == 3) dir = Direction.DOWN;
        if (value == 4) dir = Direction.LEFT;

        return dir;
    }
}
