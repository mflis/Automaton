package pl.agh.edu.Automaton.model.automatons.automaton2dim;

import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * represents Game of Life cellular automaton
 */
public class GameOfLife extends Automaton2Dim {
    private final Set<Integer> born;
    private final Set<Integer> survive;

    /**
     * @param born    represents the number of neighbours, that dead cell must have to "reborn".
     *                For example if {@code born} is [1,3,6] then dead cell must have 1 or 3 or 6 alive
     *                neighbours to become alive. Otherwise it stays dead in next generation
     * @param survive represents the number of neighbours, that alive cell must have to stay alive.
     *                For example if {@code survive} is [1,3,6] then alive cell must have 1 or 3 or 6 alive
     *                neighbours to stay alive. Otherwise it becomes dead in next generation
     * @see Automaton2Dim#Automaton2Dim(int, int, CellNeighbourhood, CellStateFactory)
     */
    public GameOfLife(int width, int height, Collection<Integer> born,
                      Collection<Integer> survive, CellNeighbourhood neighbourhood,
                      CellStateFactory factory) {

        super(width, height, neighbourhood, factory);
        this.born = new HashSet<>(born);
        this.survive = new HashSet<>(survive);
    }

    @Override
    protected Automaton newInstance(CellNeighbourhood cellNeighbourhood, CellStateFactory cellStateFactory) {
        return new GameOfLife(getWidth(), getHeight(), born, survive, cellNeighbourhood, cellStateFactory);
    }

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighbours) {
        int howManyAlive = countLiving(neighbours);

        return currentCell.getState() == BinaryState.ALIVE ?
                getState(howManyAlive, survive) : getState(howManyAlive, born);
    }

    private int countLiving(Set<Cell> neighboursStates) {
        return (int) neighboursStates.stream().filter(x -> x.getState() == BinaryState.ALIVE).count();
    }

    private CellState getState(int howManyAlive, Set<Integer> conditions) {
        return conditions.contains(howManyAlive) ? BinaryState.ALIVE : BinaryState.DEAD;
    }


}

