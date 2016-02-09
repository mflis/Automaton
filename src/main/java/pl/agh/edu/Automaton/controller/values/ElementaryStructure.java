package pl.agh.edu.Automaton.controller.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;

@AllArgsConstructor
public enum ElementaryStructure implements AbstractStructure {
    ALIVE_CELL(BinaryState.ALIVE),
    DEAD_CELL(BinaryState.DEAD);

    @Getter
    CellState state;
}
