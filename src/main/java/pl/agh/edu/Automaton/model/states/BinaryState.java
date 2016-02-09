package pl.agh.edu.Automaton.model.states;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * represents state of cell with two possible values:
 * dead or alive
 */
@AllArgsConstructor
public enum BinaryState implements CellState {
    DEAD(Color.WHITE), ALIVE(Color.BLACK);


    @Getter
    private Color color;


}
