package pl.agh.edu.Automaton.model.coordinates;

import lombok.Value;

/**
 * represents coordinates of cell on 2-dimensional board
 */
@Value
public class Coords2D implements CellCoordinates {
    /**
     * 2-dimensional x coordinate
     */
    private int x;
    /**
     * 2-dimensional y coordinate
     */
    private int y;
}
