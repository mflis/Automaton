package pl.agh.edu.Automaton.model.states;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * represents state of cell in Quad Life cellular automaton
 */
@AllArgsConstructor
public enum QuadState implements CellState {
    DEAD(Color.WHITE),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN);

    @Getter
    Color color;

}
