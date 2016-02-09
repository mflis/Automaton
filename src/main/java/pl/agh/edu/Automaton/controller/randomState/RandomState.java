package pl.agh.edu.Automaton.controller.randomState;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.agh.edu.Automaton.controller.values.AutomatonType;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords1D;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class RandomState {
    protected static final double percentageOfNotDeadCells = 30;

    int cellsInRow;
    AutomatonType automatonType;

    protected CellState getDeadCell(){
        return automatonType.getDeadCell();
    }

    protected abstract CellState getNextRandomState();

    public Map<CellCoordinates, CellState> getRandomState() {
        Map<CellCoordinates, CellState> randomMap = new HashMap<>();

        int nrOfIterations = (int) (percentageOfNotDeadCells * 0.01 * getNumberOfCells());
        for (int i = 0; i < nrOfIterations; i++) {
            randomMap.put(getRandomCoordinates(), getNextRandomState());
        }

        if (automatonType.getDim() == 2) {
            for (int x = 0; x < cellsInRow; x++) {
                for (int y = 0; y < cellsInRow; y++) {
                    randomMap.putIfAbsent(new Coords2D(x, y), getDeadCell());
                }
            }
        } else {
            for (int x = 0; x < cellsInRow; x++) {
                randomMap.putIfAbsent(new Coords1D(x), getDeadCell());
            }
        }
        return randomMap;
    }


    private CellCoordinates getRandomCoordinates() {
        int random = (int) (Math.random() * getNumberOfCells());
        if (automatonType.getDim() == 1) {
            return new Coords1D(random);
        } else {
            int x = random % cellsInRow;
            int y = random / cellsInRow;
            return new Coords2D(x, y);
        }
    }

    private int getNumberOfCells() {
        return (int) Math.pow(cellsInRow, automatonType.getDim());
    }

}
