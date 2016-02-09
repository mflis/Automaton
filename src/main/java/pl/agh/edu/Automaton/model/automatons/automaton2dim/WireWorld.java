package pl.agh.edu.Automaton.model.automatons.automaton2dim;

import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.WireElectronState;

import java.util.Set;


/**
 * represents Wireworld cellular automaton
 */
public class WireWorld extends Automaton2Dim {

    /**
     * @see Automaton2Dim#Automaton2Dim(int, int, CellNeighbourhood, CellStateFactory)
     */
    public WireWorld(int width, int height, CellNeighbourhood neighbourhood, CellStateFactory factory) {
        super(width, height, neighbourhood, factory);
    }

    @Override
    protected Automaton newInstance(CellNeighbourhood cellNeighbourhood, CellStateFactory cellStateFactory) {
        return new WireWorld(getWidth(), getHeight(), cellNeighbourhood, cellStateFactory);

    }

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighbours) {
        WireElectronState state = (WireElectronState) currentCell.getState();
        CellState newState;
        switch (state) {
            case VOID:
                newState = WireElectronState.VOID;
                break;
            case ELECTRON_HEAD:
                newState = WireElectronState.ELECTRON_TAIL;
                break;
            case ELECTRON_TAIL:
                newState = WireElectronState.WIRE;
                break;
            case WIRE:
                int heads = countElectronHeads(neighbours);
                if (heads == 1 || heads == 2) newState = WireElectronState.ELECTRON_HEAD;
                else newState = WireElectronState.WIRE;
                break;
            default:
                throw new IllegalArgumentException("code should never reach that place");
        }

        return newState;
    }

    private int countElectronHeads(Set<Cell> neighbours) {
        int howMany = 0;
        for (Cell cell : neighbours) {
            if (cell.getState() == WireElectronState.ELECTRON_HEAD) {
                howMany++;
            }
        }
        return howMany;

    }
}
