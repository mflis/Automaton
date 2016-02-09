package pl.agh.edu.Automaton.controller.randomState;

import pl.agh.edu.Automaton.controller.values.AutomatonType;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.QuadState;

public class QuadRandom extends RandomState {
    private int counter = 0;

    public QuadRandom(int cellsInRow, AutomatonType automatonType) {
        super(cellsInRow, automatonType);
    }


    @Override
    protected CellState getNextRandomState() {
        switch (counter++ % 4) {
            case 0:
                return QuadState.RED;
            case 1:
                return QuadState.GREEN;
            case 2:
                return QuadState.BLUE;
            case 3:
                return QuadState.YELLOW;
            default:
                throw new IllegalStateException("code should never reach that place");
        }
    }
}
