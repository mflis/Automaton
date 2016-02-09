package pl.agh.edu.Automaton.model.automatons.automaton2dim.langton;

import org.junit.Before;
import org.junit.Test;
import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.states.AntState;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.LangtonCell;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class AntSeekerTest {
    private AntSeeker antSeeker;

    @Before
    public void setUp() throws Exception {
        Map<Integer, AntState> leftMap = new HashMap<>();
        leftMap.put(1, AntState.NORTH);
        LangtonCell centerLangton = new LangtonCell(BinaryState.ALIVE, new HashMap<>());
        Cell center = new Cell(centerLangton, new Coords2D(4, 4));
        Cell left = new Cell(new LangtonCell(BinaryState.ALIVE, leftMap), new Coords2D(3, 4));
        Set<Cell> neihbours = new HashSet<>();
        neihbours.add(left);

        antSeeker = new AntSeeker(10, 10, center, neihbours);
    }

    @Test
    public void leftCellTest() {

        Map<Integer, AntState> expected = new HashMap<>();
        expected.put(1, AntState.EAST);
        System.out.println(antSeeker.getIncomingAnts());
        assertEquals(expected, antSeeker.getIncomingAnts());
    }


}