package pl.agh.edu.Automaton.model.automatons.automaton2dim.langton;

import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.states.AntState;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.LangtonCell;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * class used for checking which of the ant on neighbouring cells
 * should go to the center cell in nxt generation
 */
class AntSeeker {
    private final int width;
    private final int height;
    private final int centX;
    private final int centY;
    private final Map<Coords2D, LangtonCell> neighbours;

    /**
     * @param width         width of the board
     * @param height        height of the board
     * @param centerCell    current center cell
     * @param neighboursSet set of cells that are neighbours to the {@code centerCell}
     */
    AntSeeker(int width, int height, Cell centerCell, Set<Cell> neighboursSet) {
        this.width = width;
        this.height = height;
        Coords2D centerCoords = (Coords2D) centerCell.getCoordinates();
        centX = centerCoords.getX();
        centY = centerCoords.getY();
        neighbours = new HashMap<>();
        for (Cell cell : neighboursSet) {
            neighbours.put((Coords2D) cell.getCoordinates(), (LangtonCell) cell.getState());
        }
    }

    /**
     * checks which which of the ant on neighbouring cells
     * should go to the center cell in next generation
     *
     * @return map representing ants that should go to the center cell
     * in next generation
     */
    public Map<Integer, AntState> getIncomingAnts() {
        Map<Integer, AntState> result = new HashMap<>();
        result.putAll(getFrom(Direction.LEFT, new Coords2D(centX - 1, centY), new Coords2D(width - 1, centY)));
        result.putAll(getFrom(Direction.RIGHT, new Coords2D(centX + 1, centY), new Coords2D(0, centY)));
        result.putAll(getFrom(Direction.TOP, new Coords2D(centX, centY - 1), new Coords2D(centX, height - 1)));
        result.putAll(getFrom(Direction.BOTTOM, new Coords2D(centX, centY + 1), new Coords2D(centX, 0)));

        return result;
    }


    private Map<Integer, AntState> getFrom(Direction dir, Coords2D normal, Coords2D wrapped) {
        BinaryState binaryState;
        Map<Integer, AntState> allAntsFromCell;
        Map<Integer, AntState> antsGoingToThisCell = new HashMap<>();
        if (neighbours.containsKey(normal)) {
            allAntsFromCell = neighbours.get(normal).getAnts();
            binaryState = neighbours.get(normal).getCellState();
        } else if (neighbours.containsKey(wrapped)) {
            allAntsFromCell = neighbours.get(wrapped).getAnts();
            binaryState = neighbours.get(wrapped).getCellState();
        } else {
            return new HashMap<>();
        }

        allAntsFromCell.entrySet().stream().filter(ant -> shouldGoToThisCell(ant, dir, binaryState)).forEach(ant ->
                antsGoingToThisCell.put(ant.getKey(), turnAnt(ant.getValue(), binaryState))
        );

        return antsGoingToThisCell;
    }


    private AntState turnAnt(AntState antState, BinaryState colour) {
        if (colour == BinaryState.ALIVE) {
            return antState.turnRight();
        } else return antState.turnLeft();
    }


    private boolean shouldGoToThisCell(Map.Entry<Integer, AntState> ant, Direction dir, BinaryState state) {
        if (state == BinaryState.ALIVE) {
            return ant.getValue().getWhite() == dir;
        } else {
            return ant.getValue().getBlack() == dir;
        }
    }

}
