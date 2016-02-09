package pl.agh.edu.Automaton.model.automatons.automaton1dim;

import lombok.Getter;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords1D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;

/**
 * base class for 1-dimensional cellular automatons.
 */
public abstract class Automaton1Dim extends Automaton {
    /**
     * size of one dimensional board
     */
    @Getter
    private int size;

    /**
     * initializes size of board and performs {@link Automaton#initAutomaton}
     *
     * @param size size of one dimensional board
     * @see Automaton#Automaton(CellNeighbourhood, CellStateFactory)
     */
    protected Automaton1Dim(int size, CellNeighbourhood neighbourhood, CellStateFactory factory) {
        super(neighbourhood, factory);
        this.size = size;
        initAutomaton(factory);
    }

    @Override
    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords1D cell = (Coords1D) cellCoordinates;
        return cell.getX() < size - 1;
    }

    @Override
    protected CellCoordinates initialCoordinates() {
        return new Coords1D(-1);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Iteration goes from left to right
     */
    @Override
    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        assert hasNextCoordinates(cellCoordinates);

        Coords1D cell = (Coords1D) cellCoordinates;
        return new Coords1D(cell.getX() + 1);
    }


}
