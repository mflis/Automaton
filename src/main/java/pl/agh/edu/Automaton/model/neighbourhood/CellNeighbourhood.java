package pl.agh.edu.Automaton.model.neighbourhood;

import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;

import java.util.Set;

/**
 * Interface representing strategy of selecting neighbours for given cell
 */
public interface CellNeighbourhood {
    /**
     * @param cell coordinates of cell for which neighbours are searched
     * @return neighbours of {@code cell}
     */
    Set<CellCoordinates> cellNeighbours(CellCoordinates cell);
}
