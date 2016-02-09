package pl.agh.edu.Automaton.model.states;

import javafx.scene.paint.Color;
import lombok.Value;

import java.util.Map;

/**
 * represents state of cell in Langton Ant cellular automaton
 */
@Value
public class LangtonCell implements CellState {
    BinaryState cellState;
    Map<Integer, AntState> ants;

    @Override
    public Color getColor() {
        if (!ants.isEmpty()) {
            return Color.YELLOW;
        } else {
            return cellState.getColor();
        }
    }

}
