package pl.agh.edu.Automaton.model.states;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.langton.Direction;

/**
 * represents current direction of ant
 */
@AllArgsConstructor
public enum AntState {
    NONE(null, null),
    NORTH(Direction.LEFT, Direction.RIGHT),
    SOUTH(Direction.RIGHT, Direction.LEFT),
    EAST(Direction.TOP, Direction.BOTTOM),
    WEST(Direction.BOTTOM, Direction.TOP);

    /**
     * if ant is currently on white cell and have this direction
     * it should go to center cell in next generation
     * only when it's current cell has the same relative
     * position as the {@code white} field.
     * <p>
     * used in checking which of the ants on neighbouring cells should
     * go to center cell in next generation
     */
    @Getter
    private final Direction white;

    /**
     * if ant is currently on black cell and have this direction
     * it should go to center cell in next generation
     * only when it's current cell has the same relative
     * position as the {@code black} field.
     * <p>
     * used in checking which of the ants on neighbouring cells should
     * go to center cell in next generation
     */
    @Getter
    private final Direction black;

    /**
     * @return new AntState which represents Ant after turing right
     */
    public AntState turnRight() {
        AntState newState;
        switch (this) {
            case NORTH:
                newState = AntState.EAST;
                break;
            case EAST:
                newState = AntState.SOUTH;
                break;
            case SOUTH:
                newState = AntState.WEST;
                break;
            case WEST:
                newState = AntState.NORTH;
                break;
            default:
                throw new IllegalArgumentException("code should never reach that place");
        }

        return newState;

    }

    /**
     * @return new AntState which represents Ant after turing left
     */
    public AntState turnLeft() {
        AntState newState;
        switch (this) {
            case NORTH:
                newState = AntState.WEST;
                break;
            case WEST:
                newState = AntState.SOUTH;
                break;
            case SOUTH:
                newState = AntState.EAST;
                break;
            case EAST:
                newState = AntState.NORTH;
                break;
            default:
                throw new IllegalArgumentException("code should never reach that place");
        }

        return newState;

    }

}
