package pl.agh.edu.Automaton.model.stateFactory;

import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.states.CellState;

/**
 * Interface representing factory used to set initial state of {@code Automaton}
 */
public interface CellStateFactory {
    /**
     * @param cellCoordinates coordinates of cell being queried
     * @return initial state of cell at {@code cellCoordinates}
     */
    CellState initialState(CellCoordinates cellCoordinates);
}