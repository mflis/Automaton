package pl.agh.edu.Automaton.model.automatons;

import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Base class used to represent different cellular automatons
 */
public abstract class Automaton implements Iterable<Cell> {

    private final CellNeighbourhood neighboursStrategy;
    private final CellStateFactory stateFactory;
    private final Map<CellCoordinates, CellState> cells;

    /**
     * constructs {@code Automaton} object. Subclasses must use {@link Automaton#initAutomaton} to complete
     * initialization of object when the dimensions of {@code Automaton}  are known
     *
     * @param neighboursStrategy represents strategy of selecting neighbours
     * @param stateFactory       contains initial state of cellular automaton
     */
    protected Automaton(CellNeighbourhood neighboursStrategy, CellStateFactory stateFactory) {
        this.neighboursStrategy = neighboursStrategy;
        this.stateFactory = stateFactory;
        cells = new HashMap<>();

    }

    /**
     * fills the {@code Automaton} object with initial state
     * @param stateFactory contains initial state of cellular automaton
     */
    protected void initAutomaton(CellStateFactory stateFactory) {
        CellCoordinates currentCoords = initialCoordinates();
        while (hasNextCoordinates(currentCoords)) {
            currentCoords = nextCoordinates(currentCoords);
            setState(currentCoords, stateFactory.initialState(currentCoords));
        }
    }

    /**
     * computes the next generation of cellular automaton from state contained by this object
     * @return object representing next generation of cellular automaton
     */
    public Automaton nextState() {
        Automaton nextGeneration = newInstance(neighboursStrategy, stateFactory);
        for (Cell c : this) {
            Set<CellCoordinates> neighbours = neighboursStrategy.cellNeighbours(c.getCoordinates());
            Set<Cell> mappedNeighbours = mapCoordinates(neighbours);
            CellState newState = nextCellState(c, mappedNeighbours);
            nextGeneration.setState(c.getCoordinates(), newState);
        }

        return nextGeneration;
    }

    /**
     * allows to insert predefined structures, for example glider in Game of Life
     *
     * @param structure map of coordinates to be updated
     */

    //method from UML
    @SuppressWarnings("unused")
    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
        cells.putAll(structure);
    }


    public CellIterator iterator() {
        return new CellIterator();
    }

    /**
     *
     * @param cellNeighbourhood object representing strategy of selecting neighbours of cell
     * @param cellStateFactory contains initial state of cellular automaton. Needed only to construct
     * {@code Automaton} object. States from this object will be overwritten in {@link Automaton#nextState()}
     * @return empty instance of {@code Automaton} to be filled in {@link Automaton#initAutomaton} method
     */
    protected abstract Automaton newInstance(CellNeighbourhood cellNeighbourhood, CellStateFactory cellStateFactory);

    /**
     * method used in {@link CellIterator#hasNext()}
     * @param cellCoordinates coordinates of current cell in iteration
     * @return true if there is next cell to go to in this order of iteration, false otherwise
     */
    protected abstract boolean hasNextCoordinates(CellCoordinates cellCoordinates);

    /**
     * method used in {@link CellIterator} constructor
     * @return coordinates representing "-1" cell in this order of iteration
     */
    protected abstract CellCoordinates initialCoordinates();

    /**
     *  method used in {@link CellIterator#next()}
     * @param cellCoordinates current cell coordinates in this order of iteration
     * @return subsequent cell coordinates in this order of iteration
     */
    protected abstract CellCoordinates nextCoordinates(CellCoordinates cellCoordinates);

    /**
     * computes next state of {@code currentCell} based on {@code neighboursStates}
     * @param currentCell cell for which nest state will be computed
     * @param neighboursStates neighbouring cells of {@code currentCell}
     * @return state of  {@code currentCell} in next generation
     */
    protected abstract CellState nextCellState(Cell currentCell, Set<Cell> neighboursStates);

    private Set<Cell> mapCoordinates(Set<CellCoordinates> cellCoordinates) {
        return cellCoordinates.stream().map(coords -> new Cell(cells.get(coords), coords))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void setState(CellCoordinates coords, CellState state) {
        cells.put(coords, state);
    }


    public class CellIterator implements Iterator<Cell> {
        private CellCoordinates currentCoordinates;

        private CellIterator() {
            currentCoordinates = initialCoordinates();
        }

        public boolean hasNext() {
            return hasNextCoordinates(currentCoordinates);
        }

        public Cell next() {
            currentCoordinates = nextCoordinates(currentCoordinates);
            return new Cell(cells.get(currentCoordinates), currentCoordinates);
        }
    }
}
