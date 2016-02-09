package pl.agh.edu.Automaton.model.automatons.automaton2dim.langton;

import pl.agh.edu.Automaton.model.Cell;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.Automaton2Dim;
import pl.agh.edu.Automaton.model.neighbourhood.CellNeighbourhood;
import pl.agh.edu.Automaton.model.stateFactory.CellStateFactory;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.LangtonCell;

import java.util.Set;




public class LangtonAnt extends Automaton2Dim {

    /**
     * @see Automaton2Dim#Automaton2Dim(int, int, CellNeighbourhood, CellStateFactory)
     */
    public LangtonAnt(int width, int height, CellNeighbourhood neighbourhood, CellStateFactory factory) {
        super(width, height, neighbourhood, factory);
    }

    @Override
    protected Automaton newInstance(CellNeighbourhood cellNeighbourhood, CellStateFactory cellStateFactory) {
        return new LangtonAnt(getWidth(), getHeight(), cellNeighbourhood, cellStateFactory);
    }


    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighboursStates) {

        // 1 step. if current cell has one or more ant on itself then it should flip colour and dispose of ants
        // 2 step. current cell checks neighbourhood searching for ants that will come to it in next generation
        // if there are such ants cell gets this ant to herself changing theirs direction accordingly
        LangtonCell current = (LangtonCell) currentCell.getState();
        BinaryState colour = current.getCellState();
        if (hasAnts(current)) {
            colour = flipColour(colour);
        }
        AntSeeker antSeeker = new AntSeeker(getWidth(), getHeight(), currentCell, neighboursStates);
        return new LangtonCell(colour, antSeeker.getIncomingAnts());
    }

    private boolean hasAnts(LangtonCell cell) {
        return !cell.getAnts().isEmpty();
    }

    private BinaryState flipColour(BinaryState currentColour) {
        if (currentColour == BinaryState.DEAD) {
            return BinaryState.ALIVE;
        } else {
            return BinaryState.DEAD;
        }
    }


}
