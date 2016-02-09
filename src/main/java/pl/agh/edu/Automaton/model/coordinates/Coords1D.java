package pl.agh.edu.Automaton.model.coordinates;

import lombok.Value;

/**
 * represents coordinates of cell on 1-dimensional board
 */
@Value
public class Coords1D implements CellCoordinates {
    /**
     * 1-dimensional x coordinate
     */
    private int x;
}
