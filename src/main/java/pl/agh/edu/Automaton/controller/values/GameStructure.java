package pl.agh.edu.Automaton.controller.values;


import lombok.Getter;
import pl.agh.edu.Automaton.model.states.CellState;

public enum GameStructure implements AbstractStructure {
    ALIVE_CELL, DEAD_CELL, GLIDER,
    LWSS, BLINKER, BEACON, PULSAR,
    B_52_BOMBER, BAKER, BEACON_MAKER,
    BIG_GLIDER,;

    // in this case structures are complex(map instead of
    // single state) and are read from file
    @Getter
    CellState state = null;
}
