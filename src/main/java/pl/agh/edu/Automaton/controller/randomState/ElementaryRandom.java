package pl.agh.edu.Automaton.controller.randomState;

import pl.agh.edu.Automaton.controller.values.AutomatonType;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;

public class ElementaryRandom extends RandomState {

    public ElementaryRandom(int cellsInRow, AutomatonType automatonType) {
        super(cellsInRow, automatonType);
    }


    @Override
    protected CellState getNextRandomState() {
        return BinaryState.ALIVE;
    }
}
