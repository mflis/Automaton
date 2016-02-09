package pl.agh.edu.Automaton.model.states;

import javafx.scene.paint.Color;

/**
 * general interface representing state of cell in cellular automaton
 */
public interface CellState {
    /**
     * @return color representing this state of cell in visualization.
     * White always means default/dead cell
     */
    Color getColor();
}
