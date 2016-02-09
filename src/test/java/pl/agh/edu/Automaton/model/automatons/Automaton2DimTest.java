package pl.agh.edu.Automaton.model.automatons;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.Automaton2Dim;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Automaton2DimTest {
    private Automaton2Dim automaton2Dim;

    @Before
    public void setUp() {

        CellStateFactory mockFactory = Mockito.mock(CellStateFactory.class);
        CellNeighbourhood mockNeighbourhood = Mockito.mock(CellNeighbourhood.class);
        automaton2Dim = new Automaton2Dim(4, 6, mockNeighbourhood, mockFactory) {
            @Override
            protected Automaton newInstance(CellNeighbourhood cellNeighbourhood, CellStateFactory cellStateFactory) {
                return null;
            }

            @Override
            protected CellState nextCellState(Cell currentCell, Set<Cell> neighboursStates) {
                return null;
            }
        };



    }

    @Test
    public void iterationTest() {
        Set<Cell> elements = new HashSet<>();
        for (Cell curr : automaton2Dim) {
            System.out.println(curr);
            elements.add(curr);
        }
        assertEquals(4 * 6, elements.size());


    }
}