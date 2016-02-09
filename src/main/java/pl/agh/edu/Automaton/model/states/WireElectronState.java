package pl.agh.edu.Automaton.model.states;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * represents state of cell in Wireworld cellular automaton
 */
@AllArgsConstructor
public enum WireElectronState implements CellState {
    VOID(Color.WHITE),
    WIRE(Color.YELLOW),
    ELECTRON_HEAD(Color.BLUE),
    ELECTRON_TAIL(Color.RED);

    @Getter
    private Color color;
}
