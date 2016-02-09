package pl.agh.edu.Automaton.model.automatons.automaton2dim;

import lombok.Getter;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;

/**
 * base class for 2-dimensional cellular automatons.
 */
public abstract class Automaton2Dim extends Automaton {
    /**
     * width of board
     */
    @Getter
    private final int width;

    /**
     * height of board
     */
    @Getter
    private final int height;

    /**
     * initializes width, height and performs {@link Automaton#initAutomaton}
     *
     * @param width  width of board
     * @param height height of board
     * @see Automaton#Automaton(CellNeighbourhood, CellStateFactory)
     */
    protected Automaton2Dim(int width, int height, CellNeighbourhood neighbourhood, CellStateFactory factory) {
        super(neighbourhood, factory);
        this.width = width;
        this.height = height;
        initAutomaton(factory);
    }


    @Override
    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords2D cell = (Coords2D) cellCoordinates;
        return cell.getX() < width - 1 || cell.getY() < height - 1;
    }

    @Override
    protected CellCoordinates initialCoordinates() {
        return new Coords2D(-1, 0);
    }

    /**
     * {@inheritDoc}
     * <p>
     * iteration goes goes row by row from the top to bottom,
     * and from left to right in row
     */
    @Override
    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        assert hasNextCoordinates(cellCoordinates) : "that is the last cell";
        Coords2D cell = (Coords2D) cellCoordinates;
        if (cell.getX() < width - 1) {
            return new Coords2D(cell.getX() + 1, cell.getY());
        } else {
            return new Coords2D(0, cell.getY() + 1);
        }
    }


}
