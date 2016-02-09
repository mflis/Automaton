package pl.agh.edu.Automaton.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.agh.edu.Automaton.controller.randomState.*;
import pl.agh.edu.Automaton.controller.values.AbstractStructure;
import pl.agh.edu.Automaton.controller.values.AutomatonType;
import pl.agh.edu.Automaton.controller.values.GameStructure;
import pl.agh.edu.Automaton.controller.values.NeighbourHoodType;
import pl.agh.edu.Automaton.model.automatons.Automaton;
import pl.agh.edu.Automaton.model.automatons.automaton1dim.Automaton1DimImpl;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.GameOfLife;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.QuadLife;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.WireWorld;
import pl.agh.edu.Automaton.model.automatons.automaton2dim.langton.LangtonAnt;
import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords1D;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.neighbourhood.*;
import pl.agh.edu.Automaton.model.stateFactory.GeneralStateFactory;
import pl.agh.edu.Automaton.model.states.CellState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * helper class for {@code MainController}. Contains backend logic for JavaFX application
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerUtils {
    private static final Color stroke = Color.BROWN;
    private static final int frameWidth = 1;
    private static Map<CellCoordinates, CellState> initialState = new HashMap<>();
    @Setter
    private static AutomatonType automatonType;
    @Setter
    private static int cellsInRow;

    private static Canvas canvas;
    @Setter
    private static VBox settings;
    private static int generationCounter = 0;
    private static Automaton automaton;
    private static GraphicsContext graphicsContext;

    /**
     * makes certain parts of controls in additional settings visible or invisible
     * and fills the choiceBoxes to adjust it to automaton type that is
     * currently selected
     */
    public static void adjustAdditionalSettings() {
        if (automatonType == AutomatonType.ELEMENTARY) {
            settings.lookup("#elementaryRuleVbox").setVisible(true);
        } else {
            settings.lookup("#elementaryRuleVbox").setVisible(false);
        }

        if (automatonType == AutomatonType.GAME_OF_LIFE) {
            settings.lookup("#gameOfLifeRulesVbox").setVisible(true);
        } else {
            settings.lookup("#gameOfLifeRulesVbox").setVisible(false);
        }

        if (automatonType == AutomatonType.LANGTON_ANT || automatonType == AutomatonType.ELEMENTARY) {
            settings.lookup("#neighbourhoodVbox").setVisible(false);
            settings.lookup("#radiusVbox").setVisible(false);
        } else {
            settings.lookup("#radiusVbox").setVisible(true);
            settings.lookup("#neighbourhoodVbox").setVisible(true);

            @SuppressWarnings("unchecked")
            ChoiceBox<NeighbourHoodType> neighbourhood = (ChoiceBox<NeighbourHoodType>)
                    settings.lookup("#neighbourhoodChoiceBox");

            neighbourhood.getItems().setAll(NeighbourHoodType.values());
            neighbourhood.setValue(NeighbourHoodType.MOORE);

        }

        @SuppressWarnings("unchecked")
        ChoiceBox<AbstractStructure> structure = (ChoiceBox<AbstractStructure>)
                settings.lookup("#structureChoiceBox");

        structure.getItems().setAll(automatonType.getStructures());
        structure.setValue(automatonType.getInitialStructure());
    }

    /**
     * redraws board and clears the map of initial states
     */
    public static void redrawBoard() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        generationCounter = 0;
        clearMap();
        initialState.forEach(ControllerUtils::drawCell);
    }

    /**
     * computes and draws next generation of Automaton
     *
     * @param wrapping       value from wrappingChoiceBox
     * @param neighbourHood  value from neighbourhoodChoiceBox
     * @param radius         value from radiusTextField processed by "{@link MainController#updateRadius()}
     * @param born           value from bordTextField processed by "{@link MainController#updateBorn()}
     * @param survive        value from surviveTextField processed by "{@link MainController#updateSurvive()}
     * @param elementaryRule value from ruleTextField processed by "{@link MainController#updateRule()}
     */
    public static void createNextGeneration(Wrapping wrapping, NeighbourHoodType neighbourHood, int radius,
                                            Set<Integer> born, Set<Integer> survive, int elementaryRule) {

        if (generationCounter == 0) {
            fillMap();
            CellNeighbourhood cellNeighbourhood = getNeighbourhood(neighbourHood, radius, wrapping);
            GeneralStateFactory factory = new GeneralStateFactory(initialState);
            initAutomaton(born, survive, elementaryRule, cellNeighbourhood, factory);

        }
        automaton = automaton.nextState();
        generationCounter++;


        automaton.forEach(cell -> drawCell(cell.getCoordinates(), cell.getState()));

    }

    /**
     * inserts to map of initials states and draws chosen structure
     *
     * @param structure represents structure to br inserted.
     *                  Value of structureChoiceBox
     * @param mouse     mouse event from canvas onMouseClicked event
     */
    public static void insertStructure(AbstractStructure structure, MouseEvent mouse) {
        Optional<CellCoordinates> startingCell = getStartingCell((int) mouse.getX(), (int) mouse.getY());
        if (startingCell.isPresent()) {
            if (structure.getClass().equals(GameStructure.class)) {
                StructureReader reader = new StructureReader(cellsInRow, cellsInRow);
                Map<Coords2D, CellState> structureMap =
                        reader.getStructure((GameStructure) structure, (Coords2D) startingCell.get());
                initialState.putAll(structureMap);
                structureMap.forEach(ControllerUtils::drawCell);
            } else {
                initialState.put(startingCell.get(), structure.getState());
                drawCell(startingCell.get(), structure.getState());
            }
        }
    }

    private static Optional<CellCoordinates> getStartingCell(int x, int y) {
        int newX = x / getCellSize();
        int newY = y / getCellSize();

        if (automatonType.getDim() == 2) {
            if (newX < cellsInRow && newY < cellsInRow) {
                return Optional.of(new Coords2D(newX, newY));
            }
        } else {
            if (newX < cellsInRow && newY == 0) {
                return Optional.of(new Coords1D(newX));
            }
        }
        return Optional.empty();
    }

    /**
     * generates random initial state and draws this state to board
     */
    public static void generateRandomState() {
        RandomState randomGenerator;

        switch (automatonType) {
            case GAME_OF_LIFE:
                randomGenerator = new GameOfLifeRandom(cellsInRow, automatonType);
                break;
            case WIRE_WORLD:
                randomGenerator = new WireRandom(cellsInRow, automatonType);
                break;
            case LANGTON_ANT:
                randomGenerator = new LangtonRandom(cellsInRow, automatonType);
                break;
            case QUAD_LIFE:
                randomGenerator = new QuadRandom(cellsInRow, automatonType);
                break;
            case ELEMENTARY:
                randomGenerator = new ElementaryRandom(cellsInRow, automatonType);
                break;
            default:
                throw new IllegalStateException("code should never reach that place");
        }

        initialState = randomGenerator.getRandomState();
        initialState.forEach(ControllerUtils::drawCell);
    }

    /**
     * obtains reference of canvas from {@code MainController} and sets
     * color and width of frame for drawn cells
     *
     * @param newCanvas canvas instance form {@code MainController}
     */
    public static void setCanvas(Canvas newCanvas) {
        canvas = newCanvas;
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(stroke);
        graphicsContext.setLineWidth(frameWidth);
    }

    private static void initAutomaton(Set<Integer> born, Set<Integer> survive, int elementaryRule, CellNeighbourhood cellNeighbourhood, GeneralStateFactory factory) {
        switch (automatonType) {
            case GAME_OF_LIFE:
                automaton = new GameOfLife(cellsInRow, cellsInRow, born, survive, cellNeighbourhood, factory);
                break;
            case QUAD_LIFE:
                automaton = new QuadLife(cellsInRow, cellsInRow, cellNeighbourhood, factory);
                break;
            case LANGTON_ANT:
                automaton = new LangtonAnt(cellsInRow, cellsInRow, cellNeighbourhood, factory);
                break;
            case WIRE_WORLD:
                automaton = new WireWorld(cellsInRow, cellsInRow, cellNeighbourhood, factory);
                break;
            case ELEMENTARY:
                automaton = new Automaton1DimImpl(cellsInRow, elementaryRule, cellNeighbourhood, factory);
                break;
        }
    }


    private static CellNeighbourhood getNeighbourhood(NeighbourHoodType neighbourHoodType, int radius, Wrapping wrapping) {
        if (automatonType.getDim() == 2) {
            if (neighbourHoodType == NeighbourHoodType.MOORE) {
                return new MooreNeighbourHood(radius, cellsInRow, cellsInRow, wrapping);
            } else {
                return new VonNeumannNeighbourhood(radius, cellsInRow, cellsInRow, wrapping);
            }
        } else {
            return new Neighbourhood1Dim(cellsInRow, wrapping);
        }
    }

    private static void clearMap() {
        initialState.clear();
        CellState deadCell = automatonType.getDeadCell();
        if (automatonType.getDim() == 2) {
            for (int x = 0; x < cellsInRow; x++) {
                for (int y = 0; y < cellsInRow; y++) {
                    initialState.put(new Coords2D(x, y), deadCell);
                }
            }
        } else {
            for (int x = 0; x < cellsInRow; x++) {
                initialState.put(new Coords1D(x), deadCell);
            }
        }

    }

    private static void fillMap() {
        CellState deadCell = automatonType.getDeadCell();
        if (automatonType.getDim() == 2) {
            for (int x = 0; x < cellsInRow; x++) {
                for (int y = 0; y < cellsInRow; y++) {
                    initialState.putIfAbsent(new Coords2D(x, y), deadCell);
                }
            }
        } else {
            for (int x = 0; x < cellsInRow; x++) {
                initialState.putIfAbsent(new Coords1D(x), deadCell);
            }
        }

    }


    private static void drawCell(CellCoordinates coordinates, CellState state) {
        int x;
        int y;
        int cellSize = getCellSize();
        if (coordinates instanceof Coords2D) {
            x = ((Coords2D) coordinates).getX();
            y = ((Coords2D) coordinates).getY();
        } else {
            x = ((Coords1D) coordinates).getX();
            y = generationCounter % cellsInRow;
        }
        graphicsContext.setFill(state.getColor());
        graphicsContext.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
        graphicsContext.strokeRect(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    private static int getCellSize() {
        if (canvas.getHeight() < canvas.getWidth()) {
            return (int) (canvas.getHeight() / cellsInRow);
        } else {
            return (int) (canvas.getWidth() / cellsInRow);
        }
    }



}
