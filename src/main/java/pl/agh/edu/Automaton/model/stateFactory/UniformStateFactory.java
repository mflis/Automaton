package pl.agh.edu.Automaton.model.stateFactory;

import lombok.AllArgsConstructor;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.states.CellState;

/**
 * represents factory used for setting initial state to the same value for all cells
 * of {@code Automaton}. This state is specified by state provided in constructor
 */
//class from UML
@SuppressWarnings("unused")
@AllArgsConstructor

public class UniformStateFactory implements CellStateFactory {
    private final CellState state;


    @Override
    public CellState initialState(CellCoordinates cellCoordinates) {
        return state;
    }
}
