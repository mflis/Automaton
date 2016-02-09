package pl.agh.edu.Automaton.model.automatons.automaton1dim;

import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.coordinates.Coords1D;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * represents Elementary cellular automaton
 */
public class Automaton1DimImpl extends Automaton1Dim {
    private final RuleConverter ruleConverter;
    private final int rule;

    /**
     * @param rule number specifying the next color in a cell,
     *             depending on its color and its immediate neighbors.
     *             For example <a href= "http://mathworld.wolfram.com/Rule30.html"> Rule 30</a>
     * @see Automaton1Dim#Automaton1Dim(int, CellNeighbourhood, CellStateFactory)
     */
    public Automaton1DimImpl(int size, int rule, CellNeighbourhood neighbourhood, CellStateFactory factory) {
        super(size, neighbourhood, factory);
        this.rule = rule;
        ruleConverter = new RuleConverter(rule);
    }

    @Override
    protected Automaton newInstance(CellNeighbourhood cellNeighbourhood, CellStateFactory cellStateFactory) {
        return new Automaton1DimImpl(getSize(), rule, cellNeighbourhood, cellStateFactory);

    }


    /**
     * {@inheritDoc}
     * <p>
     * When currentCell is on edge of board and wrapping is OFF then we assume,that this
     * neighbour is dead
     */
    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighbours) {
        Coords1D center = (Coords1D) currentCell.getCoordinates();
        Map<Coords1D, BinaryState> neighboursMap = transformToMap(neighbours);

        Optional<BinaryState> left = getStateOfNeighbour(neighboursMap,
                new Coords1D(center.getX() - 1), new Coords1D(getSize() - 1));
        Optional<BinaryState> right = getStateOfNeighbour(neighboursMap,
                new Coords1D(center.getX() + 1), new Coords1D(0));
        BinaryState centerState = (BinaryState) currentCell.getState();

        return ruleConverter.newState(left.orElse(BinaryState.DEAD),
                centerState, right.orElse(BinaryState.DEAD));

    }

    private Optional<BinaryState> getStateOfNeighbour(Map<Coords1D,
            BinaryState> neighbours, Coords1D normal, Coords1D wrapped) {

        if (neighbours.containsKey(normal)) {
            return Optional.of(neighbours.get(normal));
        } else if (neighbours.containsKey(wrapped)) {
            return Optional.of(neighbours.get(wrapped));
        } else return Optional.empty();
    }


    private Map<Coords1D, BinaryState> transformToMap(Set<Cell> set) {
        Map<Coords1D, BinaryState> map = new HashMap<>();
        for (Cell cell : set) {
            map.put((Coords1D) cell.getCoordinates(), (BinaryState) cell.getState());
        }
        return map;
    }


}
