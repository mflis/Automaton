package pl.agh.edu.Automaton.model.automatons.automaton2dim;

import org.junit.Test;
import org.mockito.Mockito;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.QuadState;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class QuadLifeNextCellStateTest {
    private final CellStateFactory mockFactory = Mockito.mock(CellStateFactory.class);
    private final CellNeighbourhood mockNeighbourhood = Mockito.mock(CellNeighbourhood.class);
    private final QuadLife quadLife = new QuadLife(10, 10, mockNeighbourhood, mockFactory);

    @Test
    public void testNextCellStateSurvive() throws Exception {
        Set<Cell> neighbours = getNeighbours(new Integer[]{0, 1, 2, 0, 0, 0, 0, 0});
        Cell center = new Cell(QuadState.BLUE, new Coords2D(0, 1));

        QuadState result = (QuadState) quadLife.nextCellState(center, neighbours);

        assertEquals(QuadState.BLUE, result);
    }

    @Test
    public void testNextCellStateDie() throws Exception {
        Set<Cell> neighbours = getNeighbours(new Integer[]{0, 1, 0, 0, 0, 0, 0, 0});
        Cell center = new Cell(QuadState.BLUE, new Coords2D(0, 1));

        QuadState result = (QuadState) quadLife.nextCellState(center, neighbours);

        assertEquals(QuadState.DEAD, result);
    }

    @Test
    public void testNextCellStateBornAbsentColour() throws Exception {
        Set<Cell> neighbours = getNeighbours(new Integer[]{0, 1, 2, 3, 0, 0, 0, 0});
        Cell center = new Cell(QuadState.DEAD, new Coords2D(0, 1));

        QuadState result = (QuadState) quadLife.nextCellState(center, neighbours);

        assertEquals(QuadState.GREEN, result);
    }


    @Test
    public void testNextCellStateBornMajorityColour() throws Exception {
        Set<Cell> neighbours = getNeighbours(new Integer[]{0, 2, 2, 3, 0, 0, 0, 0});
        Cell center = new Cell(QuadState.DEAD, new Coords2D(0, 1));

        QuadState result = (QuadState) quadLife.nextCellState(center, neighbours);

        assertEquals(QuadState.YELLOW, result);
    }


    private Set<Cell> getNeighbours(Integer[] states) {
        Set<Cell> neighbours = new HashSet<>();
        for (int i = 0; i < states.length; i++) {
            QuadState currentState;
            switch (states[i]) {
                case 0:
                    currentState = QuadState.DEAD;
                    break;
                case 1:
                    currentState = QuadState.RED;
                    break;
                case 2:
                    currentState = QuadState.YELLOW;
                    break;
                case 3:
                    currentState = QuadState.BLUE;
                    break;
                case 4:
                    currentState = QuadState.GREEN;
                    break;
                default:
                    currentState = null;
                    break;
            }
            assert currentState != null : "states should be numbers from 0 to 4";
            neighbours.add(new Cell(currentState, new Coords2D(1, i)));
        }
        return neighbours;
    }
}