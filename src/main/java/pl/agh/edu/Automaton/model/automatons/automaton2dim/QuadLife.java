package pl.agh.edu.Automaton.model.automatons.automaton2dim;

import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.QuadState;

import java.util.*;
import java.util.stream.Collectors;

/**
 * represents Quad Life cellular automaton
 */
public class QuadLife extends Automaton2Dim {
    private static final List<Integer> surviveNumbers = Arrays.asList(2, 3);
    private static final int bornNumber = 3;

    /**
     * @see Automaton2Dim#Automaton2Dim(int, int, CellNeighbourhood, CellStateFactory)
     */
    public QuadLife(int width, int height, CellNeighbourhood neighbourhood, CellStateFactory factory) {
        super(width, height, neighbourhood, factory);
    }

    @Override
    protected Automaton newInstance(CellNeighbourhood cellNeighbourhood, CellStateFactory cellStateFactory) {
        return new QuadLife(getWidth(), getHeight(), cellNeighbourhood, cellStateFactory);
    }

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighboursStates) {
        List<QuadState> livingStates = getLivingStates(neighboursStates);
        QuadState currentState = (QuadState) currentCell.getState();

        QuadState newState;
        if (currentState != QuadState.DEAD) {
            if (surviveNumbers.contains(livingStates.size())) {
                newState = currentState;
            } else {
                newState = QuadState.DEAD;
            }
        } else {
            if (livingStates.size() == bornNumber) {
                newState = getNewAliveState(livingStates);
            } else newState = QuadState.DEAD;
        }

        return newState;
    }


    private List<QuadState> getLivingStates(Set<Cell> neighboursStates) {
        return neighboursStates.stream().filter(x -> x.getState() != QuadState.DEAD).
                map(x -> (QuadState) x.getState()).collect(Collectors.toList());
    }


    // here we assume that livingStates contains only 3 elements
    private QuadState getNewAliveState(List<QuadState> livingStates) {
        assert (livingStates.size() == bornNumber) : "livingStates.size() is different than 3";
        Map<QuadState, Integer> stateCounter = getQuadStateIntegerMap();

        for (QuadState state : livingStates) {
            int counter = stateCounter.get(state);
            if (counter > 0) return state;
            else stateCounter.put(state, counter + 1);
        }
        assert stateCounter.containsValue(0) : " At this point there should be one colour with counter =0";
        QuadState absentColour = getAbsentColour(stateCounter);
        assert (absentColour != QuadState.DEAD) : "absentColour should be anything but not Dead";

        return absentColour;
    }

    private QuadState getAbsentColour(Map<QuadState, Integer> stateCounter) {
        return stateCounter.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .findAny().map(Map.Entry::getKey)
                .orElse(QuadState.DEAD);
    }

    private Map<QuadState, Integer> getQuadStateIntegerMap() {
        Map<QuadState, Integer> stateCounter = new HashMap<>(4);
        stateCounter.put(QuadState.RED, 0);
        stateCounter.put(QuadState.GREEN, 0);
        stateCounter.put(QuadState.BLUE, 0);
        stateCounter.put(QuadState.YELLOW, 0);
        return stateCounter;
    }

}
