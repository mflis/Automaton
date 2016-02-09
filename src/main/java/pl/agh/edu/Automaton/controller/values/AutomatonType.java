package pl.agh.edu.Automaton.controller.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.agh.edu.Automaton.model.states.*;

import java.util.Arrays;
import java.util.HashMap;

@AllArgsConstructor
public enum AutomatonType {
    GAME_OF_LIFE(2, BinaryState.DEAD, GameStructure.values(), GameStructure.ALIVE_CELL),
    QUAD_LIFE(2, QuadState.DEAD, QuadStructure.values(), QuadStructure.BLUE_CELL),
    LANGTON_ANT(2, new LangtonCell(BinaryState.DEAD, new HashMap<>()), LangtonStructure.values(), LangtonStructure.ALIVE_CELL),
    WIRE_WORLD(2, WireElectronState.VOID, WireStructure.values(), WireStructure.WIRE),
    ELEMENTARY(1, BinaryState.DEAD, ElementaryStructure.values(), ElementaryStructure.ALIVE_CELL);

    @Getter
    private final int dim;

    @Getter
    private final CellState deadCell;

    private final AbstractStructure[] structures;

    @Getter
    private final AbstractStructure initialStructure;


    public AbstractStructure[] getStructures() {
        return Arrays.copyOf(structures, structures.length);
    }
}
