package pl.agh.edu.Automaton.model.neighbourhood;

import org.junit.Test;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class VonNeumannNeighbourhoodTest {
    private final VonNeumannNeighbourhood vonNeumannNeighbourhood_4_6_off = new VonNeumannNeighbourhood(1, 4, 6, Wrapping.OFF);
    private final VonNeumannNeighbourhood vonNeumannNeighbourhood_4_6_on = new VonNeumannNeighbourhood(1, 4, 6, Wrapping.ON);
    private final VonNeumannNeighbourhood vonNeumannNeighbourhood_4_6_dist_2_off = new VonNeumannNeighbourhood(2, 4, 6, Wrapping.OFF);
    private final VonNeumannNeighbourhood vonNeumannNeighbourhood_4_6_dist_2_on = new VonNeumannNeighbourhood(2, 4, 6, Wrapping.ON);


    @Test
    public void Test_2_3_false() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 3}, {2, 2}, {2, 4}, {3, 3}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(vonNeumannNeighbourhood_4_6_off.
                cellNeighbours(new Coords2D(2, 3)));

        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_2_3_true() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 3}, {2, 2}, {2, 4}, {3, 3}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(vonNeumannNeighbourhood_4_6_on.
                cellNeighbours(new Coords2D(2, 3)));

        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_0_4_false() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{0, 3}, {0, 5}, {1, 4}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(vonNeumannNeighbourhood_4_6_off.
                cellNeighbours(new Coords2D(0, 4)));

        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_0_4_true() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{0, 3}, {0, 5}, {1, 4}, {3, 4}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(vonNeumannNeighbourhood_4_6_on.
                cellNeighbours(new Coords2D(0, 4)));


        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }


    @Test
    public void Test_2_3_dist_2_false() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 3}, {2, 2}, {2, 4}, {3, 3}, {2, 1}, {1, 2}, {3, 2}, {0, 3}, {1, 4}, {3, 4}, {2, 5}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(vonNeumannNeighbourhood_4_6_dist_2_off.
                cellNeighbours(new Coords2D(2, 3)));


        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_2_3_dist_2_true() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 3}, {2, 2}, {2, 4}, {3, 3}, {2, 1}, {1, 2}, {3, 2}, {0, 3}, {1, 4}, {3, 4}, {2, 5}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(vonNeumannNeighbourhood_4_6_dist_2_on.
                cellNeighbours(new Coords2D(2, 3)));


        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    private List<CellCoordinates> getListOfNeighbours(Integer[][] coordinates) {
        List<CellCoordinates> result = new ArrayList<>();
        for (Integer[] coordinate : coordinates) {
            result.add(new Coords2D(coordinate[0], coordinate[1]));
        }
        return result;
    }
}
