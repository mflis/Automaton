package pl.agh.edu.Automaton.model.automatons.automaton1dim;

import pl.agh.edu.Automaton.model.states.BinaryState;

/**
 * Converts integer representing rule for Elementary cellular automaton into 8-bit
 * representation of rules for computing next state of cell. For example
 * <a href= "http://mathworld.wolfram.com/Rule30.html"> Rule 30</a>
 */
class RuleConverter {
    private final String rule;

    RuleConverter(int ruleAsNumber) {
        this.rule = get8BitRepresentation(ruleAsNumber);
    }

    private String get8BitRepresentation(int ruleAsNumber) {
        String binaryString = Integer.toBinaryString(ruleAsNumber);
        StringBuilder stringBuilder = new StringBuilder(binaryString).reverse();
        for (int i = stringBuilder.length(); i < 8; i++) {
            stringBuilder.append('0');
        }
        return stringBuilder.toString();
    }

    BinaryState newState(BinaryState left, BinaryState center, BinaryState right) {
        char deadOrAlive = rule.charAt(getNumberOfBit(left, center, right));
        if (deadOrAlive == '1') return BinaryState.ALIVE;
        else return BinaryState.DEAD;
    }

    private int getNumberOfBit(BinaryState left, BinaryState center, BinaryState right) {
        int nrOfBit = 0;
        if (left == BinaryState.ALIVE) nrOfBit += 4;
        if (center == BinaryState.ALIVE) nrOfBit += 2;
        if (right == BinaryState.ALIVE) nrOfBit += 1;
        return nrOfBit;
    }

}
