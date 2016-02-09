package pl.agh.edu.Automaton.controller.randomState;

import pl.agh.edu.Automaton.controller.values.AutomatonType;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.WireElectronState;

public class WireRandom extends RandomState {
    private int counter = 0;

    public WireRandom(int cellsInRow, AutomatonType automatonType) {
        super(cellsInRow, automatonType);
    }


    @Override
    protected CellState getNextRandomState() {
        if (counter++ % 5 == 0) {
            return WireElectronState.ELECTRON_HEAD;
        } else {
            return WireElectronState.WIRE;
        }

    }
}
