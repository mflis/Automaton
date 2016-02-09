package pl.agh.edu.Automaton.model.neighbourhood;

import org.junit.Test;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords1D;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class Neighbourhood1DimTest {
    private final Neighbourhood1Dim neighbourhood1DimOff = new Neighbourhood1Dim(5, Wrapping.OFF);
    private final Neighbourhood1Dim neighbourhood1DimOn = new Neighbourhood1Dim(5, Wrapping.ON);

    @Test
    public void testCellNeighboursOff() throws Exception {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[]{4, 2});

        List<CellCoordinates> testNeighbours = new ArrayList<>(neighbourhood1DimOff.cellNeighbours(new Coords1D(3)));

        //lists contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));
    }

    @Test
    public void testCellNeighboursOnLeft() throws Exception {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[]{4, 1});

        List<CellCoordinates> testNeighbours = new ArrayList<>(neighbourhood1DimOn.cellNeighbours(new Coords1D(0)));

        //lists contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));
    }

    @Test
    public void testCellNeighboursOnRight() throws Exception {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[]{3, 0});

        List<CellCoordinates> testNeighbours = new ArrayList<>(neighbourhood1DimOn.cellNeighbours(new Coords1D(4)));

        //lists contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));
    }

    @Test
    public void testCellNeighboursOffRight() throws Exception {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[]{3});

        List<CellCoordinates> testNeighbours = new ArrayList<>(neighbourhood1DimOff.cellNeighbours(new Coords1D(4)));

        //lists contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));
    }

    private List<CellCoordinates> getListOfNeighbours(Integer[] coordinates) {
        List<CellCoordinates> result = new ArrayList<>();
        for (Integer coordinate : coordinates) {
            result.add(new Coords1D(coordinate));
        }
        return result;
    }
}