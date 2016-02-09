package pl.agh.edu.Automaton.model.stateFactory;

import lombok.AllArgsConstructor;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.Map;

/**
 * represents factory used for setting initial states of {@code Automaton}
 * specified by map of states provided in constructor
 */
@AllArgsConstructor
public class GeneralStateFactory implements CellStateFactory {
    //states are initialized by Lombok @AllArgsConstructor
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final Map<CellCoordinates, CellState> states;

    @Override
    public CellState initialState(CellCoordinates cellCoordinates) {
        return states.get(cellCoordinates);
    }
}
