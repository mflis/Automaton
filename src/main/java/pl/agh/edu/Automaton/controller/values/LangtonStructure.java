package pl.agh.edu.Automaton.controller.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.agh.edu.Automaton.model.states.AntState;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.LangtonCell;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

@RequiredArgsConstructor
public enum LangtonStructure implements AbstractStructure {
    ALIVE_CELL(new LangtonCell(BinaryState.ALIVE, new HashMap<>())),
    DEAD_CELL(new LangtonCell(BinaryState.DEAD, new HashMap<>())),
    ANT_NORTH(new LangtonCell(BinaryState.ALIVE, new HashMap<>(Collections.singletonMap(getId(), AntState.NORTH)))),
    ANT_WEST(new LangtonCell(BinaryState.ALIVE, new HashMap<>(Collections.singletonMap(getId(), AntState.WEST)))),
    ANT_SOUTH(new LangtonCell(BinaryState.ALIVE, new HashMap<>(Collections.singletonMap(getId(), AntState.SOUTH)))),
    ANT_EAST(new LangtonCell(BinaryState.ALIVE, new HashMap<>(Collections.singletonMap(getId(), AntState.EAST))));

    @Getter
    private final CellState state;


    private static int getId() {
        Random random = new Random();
        return random.nextInt(5000);
    }
}
