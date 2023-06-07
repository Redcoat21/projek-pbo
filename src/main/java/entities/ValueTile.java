package entities;

public class ValueTile {
    private double value;
    private int x;
    private int y;
    Direction moved;

    public ValueTile(double value, int x, int y,Direction moved) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.moved=moved;
    }

    public Direction getMoved() {
        return moved;
    }

    public double getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
