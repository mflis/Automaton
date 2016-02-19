package pl.agh.edu.Automaton.model.neighbourhood;

import pl.agh.edu.Automaton.model.coordinates.Coords2D;

/**
 * base class for 2-dimensional neighbourhood strategies
 */
public abstract class NeighbourHood2D implements CellNeighbourhood {
    int distance;
    Wrapping wrapping;
    private int width;
    private int height;

    /**
     * @param distance represents range on which neighbours are searched
     * @param width    width of the board
     * @param height   height of the board
     * @param wrapping parameter telling if board should wrapped on the borders or not
     */
    protected NeighbourHood2D(int distance, int width, int height, Wrapping wrapping) {
        if (distance < 1 || width < 1 || height < 1) {
            throw new IllegalArgumentException("arguments can't be less than 1");
        }
        this.distance = distance;
        this.width = width;
        this.height = height;
        this.wrapping = wrapping;
    }


    private boolean isOutsideX(int x) {
        return x < 0 || x >= width;
    }

    private boolean isOutsideY(int y) {
        return y < 0 || y >= height;
    }

    boolean isOutside(int x, int y) {
        return isOutsideX(x) || isOutsideY(y);
    }


    private int mirrorX(int x) {
        assert isOutsideX(x) : "you're trying to get X mirror of normal cell";
        return Math.abs(Math.abs(x) - width) % width;
    }

    private int mirrorY(int y) {
        assert isOutsideY(y) : "you're trying to get Y mirror of normal cell";
        return Math.abs(Math.abs(y) - height) % height;
    }

    Coords2D getWrappedCoords2D(int x, int y) {
        Coords2D current;
        int xCoord = isOutsideX(x) ? mirrorX(x) : x;
        int yCoord = isOutsideY(y) ? mirrorY(y) : y;
        current = new Coords2D(xCoord, yCoord);
        return current;
    }


}
