package game.map.heperForGalaxyGenerator;

import game.map.StarSystem;

import static game.Utils.numberBetween;

public class Square {
    int column;
    int row;
    int side;
    int shift;
    int maxX;
    int minX;
    int maxY;
    int minY;
    boolean haveStar = false;

    public Square(int column, int row, int side, int shift) {
        this.column = column;
        this.row = row;
        this.side = side;
        this.shift = shift;
        minX = (column - 1) * side + shift;
        maxX = column * side + shift;
        minY = (row - 1) * side + shift;
        maxY = row * side + shift;

    }

    public boolean inSquare(int x, int y) {
        return x > minX && x < maxX && y > minY && y < maxY;
    }

    public StarSystem randomSystemInSquare() {
        return new StarSystem(numberBetween(minX, maxX), numberBetween(minY, maxY));
    }

    public StarSystem minSys() {
        return new StarSystem(minX, minY);
    }

    public StarSystem maxSys() {
        return new StarSystem(maxX, maxY);
    }
}
