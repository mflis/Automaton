package pl.agh.edu.Automaton.model.neighbourhood;

import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords1D;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class representing strategy of searching neighbours in Elementary cellular automatons.
 * In this strategy set of neighbours consists of two cells:
 * cell on the right and cell on the left.
 */
public class Neighbourhood1Dim implements CellNeighbourhood {
    private Wrapping wrapping;
    private int boardLength;

    /**
     * @param boardLength length of 1-dimensional board
     * @param wrapping    parameter telling if board should wrapped on the borders or not
     */
    public Neighbourhood1Dim(int boardLength, Wrapping wrapping) {
        if (boardLength < 1) {
            throw new IllegalArgumentException("boardLength can't be less than 1");
        }
        this.boardLength = boardLength;
        this.wrapping = wrapping;
    }

    @Override
    public Set<CellCoordinates> cellNeighbours(CellCoordinates cell) {
        Coords1D current = (Coords1D) cell;
        Integer left = current.getX() - 1;
        Integer right = current.getX() + 1;
        Set<CellCoordinates> result = new LinkedHashSet<>();
        Coords1D leftCoords = getCoordinate(left);
        Coords1D rightCoords = getCoordinate(right);
        if (leftCoords != null) {
            result.add(leftCoords);
        }
        if (rightCoords != null) {
            result.add(rightCoords);
        }
        return result;

    }

    private Coords1D getCoordinate(Integer x) {
        if (isOutside(x)) {
            if (wrapping == Wrapping.ON) {
                return new Coords1D(mirrorX(x));
            } else {
                return null;
            }
        } else {
            return new Coords1D(x);
        }
    }

    private boolean isOutside(int x) {
        return x < 0 || x >= boardLength;
    }

    private int mirrorX(int x) {
        assert isOutside(x) : "you're trying to invert normal cell";
        return boardLength - Math.abs(x);
    }

}
