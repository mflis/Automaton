package pl.agh.edu.Automaton.controller.randomState;

import pl.agh.edu.Automaton.controller.values.AutomatonType;
import pl.agh.edu.Automaton.model.states.AntState;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.LangtonCell;

import java.util.HashMap;
import java.util.Map;

public class LangtonRandom extends RandomState {
    private long counter = 0;
    private int antIdCounter = 0;

    public LangtonRandom(int cellsInRow, AutomatonType automatonType) {
        super(cellsInRow, automatonType);
    }


    @Override
    protected CellState getNextRandomState() {
        Map<Integer, AntState> antStateMap;
        BinaryState cellState;
        if (counter++ % 10 == 0) {
            antStateMap = getIntegerAntStateMap();
        } else {
            antStateMap = new HashMap<>();
        }
        if (counter % 2 == 1) {
            cellState = BinaryState.ALIVE;
        } else {
            cellState = BinaryState.DEAD;
        }
        return new LangtonCell(cellState, antStateMap);
    }

    private Map<Integer, AntState> getIntegerAntStateMap() {
        Map<Integer, AntState> antStateMap = new HashMap<>();
        AntState ant;
        switch (antIdCounter++ % 4) {
            case 0:
                ant = AntState.EAST;
                break;
            case 1:
                ant = AntState.SOUTH;
                break;
            case 2:
                ant = AntState.WEST;
                break;
            case 3:
                ant = AntState.NORTH;
                break;
            default:
                throw new IllegalStateException("code should never reach that place");
        }
        antStateMap.put(antIdCounter, ant);
        return antStateMap;
    }
}
