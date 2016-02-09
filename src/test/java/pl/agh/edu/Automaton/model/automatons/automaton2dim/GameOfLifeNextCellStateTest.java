package pl.agh.edu.Automaton.model.automatons.automaton2dim;

import org.junit.Test;
import org.mockito.Mockito;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.BinaryState;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GameOfLifeNextCellStateTest {
    private final CellStateFactory mockFactory = Mockito.mock(CellStateFactory.class);
    private final CellNeighbourhood mockNeighbourhood = Mockito.mock(CellNeighbourhood.class);
    private final GameOfLife gameOfLife = new GameOfLife(10, 10, Collections.singletonList(3), Arrays.asList(2, 3), mockNeighbourhood, mockFactory);

    @Test
    public void testNextCellStateBorn() throws Exception {
        Set<Cell> neighbours = getNeighbours(new Boolean[]{true, true, true, false, false, false, false, false});
        Cell center = new Cell(BinaryState.DEAD, new Coords2D(1, 2));

        BinaryState result = (BinaryState) gameOfLife.nextCellState(center, neighbours);

        assertEquals(BinaryState.ALIVE, result);
    }

    @Test
    public void testNextCellStateSurvive() throws Exception {
        Set<Cell> neighbours = getNeighbours(new Boolean[]{true, true, false, false, false, false, false, false});
        Cell center = new Cell(BinaryState.ALIVE, new Coords2D(1, 2));

        BinaryState result = (BinaryState) gameOfLife.nextCellState(center, neighbours);

        assertEquals(BinaryState.ALIVE, result);
    }

    @Test
    public void testNextCellStateDie() throws Exception {
        Set<Cell> neighbours = getNeighbours(new Boolean[]{true, true, true, true, false, false, false, false});
        Cell center = new Cell(BinaryState.ALIVE, new Coords2D(1, 2));

        BinaryState result = (BinaryState) gameOfLife.nextCellState(center, neighbours);

        assertEquals(BinaryState.DEAD, result);
    }

    private Set<Cell> getNeighbours(Boolean[] states) {
        Set<Cell> neighbours = new HashSet<>();
        for (int i = 0; i < states.length; i++) {
            if (states[i]) {
                neighbours.add(new Cell(BinaryState.ALIVE, new Coords2D(i, 1)));
            } else {
                neighbours.add(new Cell(BinaryState.DEAD, new Coords2D(i, 1)));
            }

        }
        return neighbours;
    }

}