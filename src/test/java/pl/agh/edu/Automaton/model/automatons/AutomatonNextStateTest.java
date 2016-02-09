package pl.agh.edu.Automaton.model.automatons;

import org.junit.Test;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.GameOfLife;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.neighbourhood.MooreNeighbourHood;
import pl.agh.edu.Automaton.model.neighbourhood.Wrapping;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.stateFactory.GeneralStateFactory;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AutomatonNextStateTest {
    private static final int width = 3;
    private static final int height = 3;
    private final CellNeighbourhood mooreOff = new MooreNeighbourHood(1, width, height, Wrapping.OFF);


    @Test
    public void nextStateTest() {
        Integer[] initialStates = new Integer[]{0, 1, 0, 0, 0, 0, 1, 0, 1};
        Integer[] expectedStates = new Integer[]{0, 0, 0, 0, 1, 0, 0, 0, 0};
        Map<CellCoordinates, CellState> initialCellMap = getMap(initialStates, width, height);
        Map<CellCoordinates, CellState> expectedCellMap = getMap(expectedStates, width, height);
        CellStateFactory intialFactory = new GeneralStateFactory(initialCellMap);
        Automaton initial = new GameOfLife(width, height, Collections.singletonList(3),
                Arrays.asList(2, 3), mooreOff, intialFactory);

        Automaton nextGen = initial.nextState();

        Map<CellCoordinates, CellState> nextGenMap = new HashMap<>();
        for (Cell c : nextGen) {
            nextGenMap.put(c.getCoordinates(), c.getState());
        }

        assertEquals(expectedCellMap, nextGenMap);


    }


    private Map<CellCoordinates, CellState> getMap(Integer[] states, int width, int height) {
        Map<CellCoordinates, CellState> mapOfCells = new HashMap<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mapOfCells.put(new Coords2D(x, y), intToBin(states[x + y * width]));
            }
        }
        return mapOfCells;
    }


    private BinaryState intToBin(int value) {
        if (value == 0) return BinaryState.DEAD;
        else if (value == 1) return BinaryState.ALIVE;
        else throw new IllegalArgumentException("values can only be 0 or 1");
    }


}