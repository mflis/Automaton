package pl.agh.edu.Automaton.model.automatons;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.automaton1dim.Automaton1Dim;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class Automaton1DimTest {
    private Automaton1Dim automaton1Dim;

    @Before
    public void setUp() {
        CellStateFactory mockFactory = Mockito.mock(CellStateFactory.class);
        CellNeighbourhood mockNeighbourhood = Mockito.mock(CellNeighbourhood.class);
        automaton1Dim = new Automaton1Dim(10, mockNeighbourhood, mockFactory) {
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
    public void iterationTest() throws Exception {
        Set<Cell> elements = new HashSet<>();
        for (Cell anAutomaton1Dim : automaton1Dim) {
            elements.add(anAutomaton1Dim);
            System.out.println(anAutomaton1Dim);
        }
        assertEquals(10, elements.size());
    }

}