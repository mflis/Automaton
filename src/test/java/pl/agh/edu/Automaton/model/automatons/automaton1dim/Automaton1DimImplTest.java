package pl.agh.edu.Automaton.model.automatons.automaton1dim;

import org.junit.Test;
import org.mockito.Mockito;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.coordinates.Coords1D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.BinaryState;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Automaton1DimImplTest {
    private final CellStateFactory mockFactory = Mockito.mock(CellStateFactory.class);
    private final CellNeighbourhood mockNeighbourhood = Mockito.mock(CellNeighbourhood.class);
    private final Automaton1DimImpl automaton1Dim = new Automaton1DimImpl(10, 30, mockNeighbourhood, mockFactory);

    @Test
    public void testNextCellState() throws Exception {
        Cell center = new Cell(BinaryState.ALIVE, new Coords1D(5));
        Cell left = new Cell(BinaryState.ALIVE, new Coords1D(4));
        Cell right = new Cell(BinaryState.ALIVE, new Coords1D(6));

        Set<Cell> neighbours = getNeighboursSet(left, right);

        BinaryState result = (BinaryState) automaton1Dim.nextCellState(center, neighbours);
        assertEquals(BinaryState.DEAD, result);
    }

    @Test
    public void testNextCellStateWithWrapping() throws Exception {
        Cell center = new Cell(BinaryState.ALIVE, new Coords1D(9));
        Cell left = new Cell(BinaryState.ALIVE, new Coords1D(8));
        Cell right = new Cell(BinaryState.ALIVE, new Coords1D(0));

        Set<Cell> neighbours = getNeighboursSet(left, right);

        BinaryState result = (BinaryState) automaton1Dim.nextCellState(center, neighbours);
        assertEquals(BinaryState.DEAD, result);
    }

    private Set<Cell> getNeighboursSet(Cell left, Cell right) {
        Set<Cell> neighbours = new HashSet<>();
        neighbours.add(left);
        neighbours.add(right);
        return neighbours;
    }
}