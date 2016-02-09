package pl.agh.edu.Automaton.controller.values;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.agh.edu.Automaton.model.states.CellState;
import pl.agh.edu.Automaton.model.states.WireElectronState;

@AllArgsConstructor
public enum WireStructure implements AbstractStructure {
    ELECTRON_HEAD(WireElectronState.ELECTRON_HEAD),
    ELECTRON_TAIL(WireElectronState.ELECTRON_TAIL),
    WIRE(WireElectronState.WIRE),
    VOID(WireElectronState.VOID);

    @Getter
    CellState state;
}
