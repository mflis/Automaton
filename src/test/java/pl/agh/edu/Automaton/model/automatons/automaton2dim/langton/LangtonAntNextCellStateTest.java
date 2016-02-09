package pl.agh.edu.Automaton.model.automatons.automaton2dim.langton;

import org.junit.Test;
import org.mockito.Mockito;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.AntState;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.LangtonCell;

import java.util.*;

import static org.junit.Assert.assertEquals;


@SuppressWarnings("ALL")
public class LangtonAntNextCellStateTest {
    private final CellStateFactory mockFactory = Mockito.mock(CellStateFactory.class);
    private final CellNeighbourhood mockNeighbourhood = Mockito.mock(CellNeighbourhood.class);
    private final LangtonAnt langton = new LangtonAnt(5, 8, mockNeighbourhood, mockFactory);

    @Test
    public void testNextCellState() throws Exception {
        // given
        Map<Integer, AntState> centerMap = new HashMap<>();
        addToMap(centerMap, new Integer[]{1, 2}, new AntState[]{AntState.NORTH, AntState.EAST});
        Cell center = new Cell(new LangtonCell(BinaryState.ALIVE, centerMap), new Coords2D(4, 4));

        Map<Integer, AntState> leftMap = new HashMap<>();
        leftMap.put(3, AntState.NORTH);
        Cell rightWrapped = new Cell(new LangtonCell(BinaryState.DEAD, leftMap), new Coords2D(0, 4));

        @SuppressWarnings("UnnecessaryLocalVariable") Map<Integer, AntState> topMap = centerMap;
        Cell top = new Cell(new LangtonCell(BinaryState.ALIVE, topMap), new Coords2D(4, 3));

        Map<Integer, AntState> bottomMap = new HashMap<>();
        addToMap(bottomMap, new Integer[]{6, 7}, new AntState[]{AntState.NORTH, AntState.EAST});
        Cell bottom = new Cell(new LangtonCell(BinaryState.DEAD, bottomMap), new Coords2D(4, 5));

        Cell left = new Cell(new LangtonCell(BinaryState.ALIVE, new HashMap<>()), new Coords2D(3, 4));

        Set<Cell> neighbours = new HashSet<>();
        neighbours.addAll(Arrays.asList(top, rightWrapped, bottom, left));

        Map<Integer, AntState> expectedMap = new HashMap<>();
        addToMap(expectedMap, new Integer[]{2, 3, 7}, new AntState[]{AntState.SOUTH, AntState.WEST, AntState.NORTH});
        LangtonCell expectedCell = new LangtonCell(BinaryState.DEAD, expectedMap);


        //when
        LangtonCell result = (LangtonCell) langton.nextCellState(center, neighbours);


        //then
        assertEquals(expectedCell, result);

    }

    private void addToMap(Map<Integer, AntState> map, Integer[] integer, AntState[] ant) {
        for (int i = 0; i < integer.length; i++) {
            map.put(integer[i], ant[i]);
        }
    }
}