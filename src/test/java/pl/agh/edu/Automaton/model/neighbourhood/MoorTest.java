package pl.agh.edu.Automaton.model.neighbourhood;

import org.junit.Test;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class MoorTest {
    private final MooreNeighbourHood mooreNeighbourHood_4_6_off = new MooreNeighbourHood(1, 4, 6, Wrapping.OFF);
    private final MooreNeighbourHood mooreNeighbourHood_4_6_on = new MooreNeighbourHood(1, 4, 6, Wrapping.ON);
    private final MooreNeighbourHood mooreNeighbourHood_4_6_dist_2_off = new MooreNeighbourHood(2, 4, 6, Wrapping.OFF);
    private final MooreNeighbourHood mooreNeighbourHood_4_6_dist_2_on = new MooreNeighbourHood(2, 4, 6, Wrapping.ON);


    @Test
    public void Test_2_3_false() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 2}, {1, 3}, {1, 4}, {2, 2}, {2, 4}, {3, 2}, {3, 3}, {3, 4}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(mooreNeighbourHood_4_6_off.
                cellNeighbours(new Coords2D(2, 3)));

        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_2_3_true() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 2}, {1, 3}, {1, 4}, {2, 2}, {2, 4}, {3, 2}, {3, 3}, {3, 4}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(mooreNeighbourHood_4_6_on.
                cellNeighbours(new Coords2D(2, 3)));

        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_0_4_false() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{0, 3}, {0, 5}, {1, 3}, {1, 4}, {1, 5}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(mooreNeighbourHood_4_6_off.
                cellNeighbours(new Coords2D(0, 4)));

        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_0_4_true() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{0, 3}, {0, 5}, {1, 3}, {1, 4}, {1, 5}, {3, 3}, {3, 4}, {3, 5}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(mooreNeighbourHood_4_6_on.
                cellNeighbours(new Coords2D(0, 4)));


        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }


    @Test
    public void Test_2_3_dist_2_false() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 2}, {1, 3}, {1, 4}, {2, 2}, {2, 4}, {3, 2}, {3, 3}, {3, 4},
                        {0, 1}, {1, 1}, {2, 1}, {3, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 5}, {2, 5}, {3, 5}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(mooreNeighbourHood_4_6_dist_2_off.
                cellNeighbours(new Coords2D(2, 3)));


        //list contain the same elements ignoring order
        assertTrue(testNeighbours.containsAll(expectedNeighbours) && expectedNeighbours.containsAll(testNeighbours));

    }

    @Test
    public void Test_2_3_dist_2_true() {
        List<CellCoordinates> expectedNeighbours = getListOfNeighbours(new Integer[][]
                {{1, 2}, {1, 3}, {1, 4}, {2, 2}, {2, 4}, {3, 2}, {3, 3}, {3, 4},
                        {0, 1}, {1, 1}, {2, 1}, {3, 1}, {0, 2}, {0, 3}, {0, 4},
                        {0, 5}, {1, 5}, {2, 5}, {3, 5}});

        List<CellCoordinates> testNeighbours = new ArrayList<>(mooreNeighbourHood_4_6_dist_2_on.
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
