package pl.agh.edu.Automaton.model.automatons.automaton1dim;

import org.junit.Test;
import pl.agh.edu.Automaton.model.states.BinaryState;

import static org.junit.Assert.assertEquals;

public class RuleConverterTest {

    @Test
    public void testRule30() throws Exception {
        RuleConverter ruleConverter = new RuleConverter(30);
        BinaryState state7 = ruleConverter.newState(BinaryState.ALIVE, BinaryState.ALIVE, BinaryState.ALIVE);
        BinaryState state3 = ruleConverter.newState(BinaryState.DEAD, BinaryState.ALIVE, BinaryState.ALIVE);
        BinaryState state0 = ruleConverter.newState(BinaryState.DEAD, BinaryState.DEAD, BinaryState.DEAD);
        assertEquals(BinaryState.DEAD, state7);
        assertEquals(BinaryState.ALIVE, state3);
        assertEquals(BinaryState.DEAD, state0);
    }

    @Test
    public void testRule110() throws Exception {
        RuleConverter ruleConverter = new RuleConverter(110);
        BinaryState state7 = ruleConverter.newState(BinaryState.ALIVE, BinaryState.ALIVE, BinaryState.ALIVE);
        BinaryState state5 = ruleConverter.newState(BinaryState.ALIVE, BinaryState.DEAD, BinaryState.ALIVE);
        BinaryState state0 = ruleConverter.newState(BinaryState.DEAD, BinaryState.DEAD, BinaryState.DEAD);
        assertEquals(BinaryState.DEAD, state7);
        assertEquals(BinaryState.ALIVE, state5);
        assertEquals(BinaryState.DEAD, state0);
    }
}