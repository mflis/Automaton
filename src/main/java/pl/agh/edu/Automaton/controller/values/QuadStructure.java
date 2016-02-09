package pl.agh.edu.Automaton.controller.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.QuadState;

@AllArgsConstructor
public enum QuadStructure implements AbstractStructure {
    RED_CELL(QuadState.RED),
    BLUE_CELL(QuadState.BLUE),
    GREEN_CELL(QuadState.GREEN),
    YELLOW_CELL(QuadState.YELLOW),
    DEAD_CELL(QuadState.DEAD);

    @Getter
    CellState state;
}
