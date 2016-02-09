package pl.agh.edu.Automaton.model;


import lombok.Value;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.states.CellState;

/**
 * general interface representing cell in cellular automatons
 */
@Value
public class Cell {
    private final CellState state;
    private final CellCoordinates coordinates;
}
