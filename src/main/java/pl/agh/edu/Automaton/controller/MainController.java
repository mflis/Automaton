package pl.agh.edu.Automaton.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.agh.edu.Automaton.controller.values.AbstractStructure;
import pl.agh.edu.Automaton.controller.values.AutomatonType;
import pl.agh.edu.Automaton.controller.values.NeighbourHoodType;
import pl.agh.edu.Automaton.model.neighbourhood.Wrapping;

import java.util.*;

/**
 * Controller class for JavaFX application
 */
public class MainController {
    private final Duration millis = Duration.millis(200);
    @FXML
    private Slider cellsInRowSlider;
    @FXML
    private VBox generalSettingsVbox;
    @FXML
    private VBox automatonTypeVbox;
    @FXML
    private ChoiceBox<AutomatonType> automatonTypeChoiceBox;
    @FXML
    private ChoiceBox<NeighbourHoodType> neighbourhoodChoiceBox;
    @FXML
    private TextField radiusTextField;
    @FXML
    private TextField bornTextField;
    @FXML
    private TextField surviveTextField;
    @FXML
    private ChoiceBox<AbstractStructure> structureChoiceBox;
    @FXML
    private AnchorPane canvasParentPane;
    @FXML
    private VBox additionalSettings;
    @FXML
    private ChoiceBox<Wrapping> wrappingChoiceBox;
    @FXML
    private Button runButton;
    @FXML
    private TextField ruleTextField;
    @FXML
    private Button randomButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button exitButton;
    private int radius = 1;
    private Set<Integer> born = new HashSet<>(Collections.singletonList(3));
    private Set<Integer> survive = new HashSet<>(Arrays.asList(2, 3));
    private int rule = 30;
    private boolean isRunning = false;
    private Timeline timeline;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @SuppressWarnings("unused")
    @FXML
    private void initialize() {
        initializeAndBindMethodsToCanvas();
        ControllerUtils.setSettings(additionalSettings);
        initializeAndBindMethodsToAutomatonType();
        initializeWrapping();
        initializeAndBindMethodsToSlider();
        initializeTimeline();
        setListenersOnTextFields();

    }

    private void initializeAndBindMethodsToSlider() {
        // matrix redraws when cellSlider or automaton type is changed
        cellsInRowSlider.valueProperty().addListener(observable -> {
            ControllerUtils.setCellsInRow((int) cellsInRowSlider.getValue());
            ControllerUtils.redrawBoard();

        });
        ControllerUtils.setCellsInRow((int) cellsInRowSlider.getValue());
    }

    private void initializeWrapping() {
        // wrapping initialization
        wrappingChoiceBox.getItems().setAll(Wrapping.values());
        wrappingChoiceBox.setValue(Wrapping.ON);
    }

    private void initializeAndBindMethodsToAutomatonType() {
        //automaton type initialization
        automatonTypeChoiceBox.getItems().setAll(AutomatonType.values());
        automatonTypeChoiceBox.setValue(AutomatonType.GAME_OF_LIFE);
        //initial adjustment of additional settings
        ControllerUtils.setAutomatonType(automatonTypeChoiceBox.getValue());
        ControllerUtils.adjustAdditionalSettings();

        //additional settings are shown or hidden depending on automaton type
        automatonTypeChoiceBox.valueProperty().addListener(observable -> {
            ControllerUtils.setAutomatonType(automatonTypeChoiceBox.getValue());
            ControllerUtils.adjustAdditionalSettings();
            ControllerUtils.redrawBoard();
        });
    }

    private void initializeAndBindMethodsToCanvas() {
        //create canvas
        Canvas canvas = new Canvas();
        canvasParentPane.getChildren().add(canvas);

        // pass reference to ControllerUtils
        ControllerUtils.setCanvas(canvas);

        // code needed so that drawing action could work properly
        canvas.widthProperty().bind(canvasParentPane.widthProperty());
        canvas.heightProperty().bind(canvasParentPane.heightProperty());
        canvas.widthProperty().addListener(observable -> ControllerUtils.redrawBoard());
        canvas.heightProperty().addListener(observable -> ControllerUtils.redrawBoard());
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                ControllerUtils.insertStructure(structureChoiceBox.getValue(), mouseEvent)
        );
    }

    /**
     * connected with to "Random State" button  via on Action property
     */
    @FXML
    public void randomAction() {
        ControllerUtils.generateRandomState();
    }

    /**
     * connected with to "Run" button   via on Action property
     */
    @FXML
    public void runAction() {
        if (!isRunning) {
            isRunning = true;
            runButton.setText("Stop");
            timeline.play();

        } else {
            isRunning = false;
            runButton.setText("Run");
            timeline.pause();
        }
        clearButton.setDisable(isRunning);
    }

    private void initializeTimeline() {
        timeline = new Timeline(new KeyFrame(millis, actionEvent -> nextGenAction()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * connected with to "Next Gen" button   via on Action property
     */
    @FXML
    public void nextGenAction() {
        disableSettings(true);
        ControllerUtils.createNextGeneration(wrappingChoiceBox.getValue(), neighbourhoodChoiceBox.getValue(),
                radius, born, survive, rule);
    }

    private void disableSettings(boolean value) {
        additionalSettings.setDisable(value);
        generalSettingsVbox.setDisable(value);
        automatonTypeVbox.setDisable(value);
        randomButton.setDisable(value);
    }

    /**
     * connected with to "Clear" button  via on Action property
     */
    public void clearAction() {
        disableSettings(false);
        ControllerUtils.redrawBoard();
    }

    /**
     * connected with to "Exit" button via on Action property
     */
    @FXML
    public void exitAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void setListenersOnTextFields() {
        radiusTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                    if (!newValue) { //when focus lost
                        updateRadius();
                    }
                }
        );

        bornTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                    if (!newValue) { //when focus lost
                        updateBorn();
                    }
                }
        );

        surviveTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                    if (!newValue) { //when focus lost
                        updateSurvive();
                    }
                }
        );


        ruleTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                    if (!newValue) { //when focus lost
                        updateRule();
                    }
                }
        );
    }

    /**
     * connected with to "Elementary Automaton Rule" text field via on Action property
     * and with focusedProperty of this textfield
     */
    @FXML
    public void updateRule() {
        try {
            rule = Integer.parseUnsignedInt(ruleTextField.getText());
            if (rule > 255) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            ruleTextField.setText("30");
            rule = 30;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("rule must be integer integer between 1 and 255");
            alert.showAndWait();
        }
    }

    /**
     * connected with to "Radius" text field via on Action property
     * and with focusedProperty of this textfield
     */
    @FXML
    public void updateRadius() {
        try {
            radius = Integer.parseInt(radiusTextField.getText());
            if (radius < 1) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            radiusTextField.setText("1");
            radius = 1;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("radius must be positive integer");
            alert.showAndWait();
        }
    }

    /**
     * connected with to "Born" text field via on Action property
     * and with focusedProperty of this textfield
     */
    @FXML
    public void updateBorn() {
        Set<Integer> updatedSet = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(bornTextField.getText(), ",");
        try {
            while (tokenizer.hasMoreTokens()) {
                updatedSet.add(Integer.parseUnsignedInt(tokenizer.nextToken()));
            }
        } catch (NumberFormatException ex) {
            bornTextField.setText("3");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("values must be positive integers");
            alert.showAndWait();
        }

        born = updatedSet;
    }

    /**
     * connected with to "Survive" text field via on Action property
     * and with focusedProperty of this textfield
     */
    @FXML
    public void updateSurvive() {
        Set<Integer> updatedSet = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(surviveTextField.getText(), ",");
        try {
            while (tokenizer.hasMoreTokens()) {
                updatedSet.add(Integer.parseUnsignedInt(tokenizer.nextToken()));
            }
        } catch (NumberFormatException ex) {
            surviveTextField.setText("2,3");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("values must be positive integers");
            alert.showAndWait();
        }

        survive = updatedSet;
    }


}
