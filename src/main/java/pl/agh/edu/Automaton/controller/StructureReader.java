package pl.agh.edu.Automaton.controller;

import lombok.RequiredArgsConstructor;
import pl.agh.edu.Automaton.controller.values.GameStructure;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;
import pl.agh.edu.Automaton.model.states.BinaryState;
import pl.agh.edu.Automaton.model.states.CellState;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class StructureReader {

    private final int widthOfBoard;
    private final int heightOfBoard;
    private Map<Coords2D, CellState> rawStructure = new HashMap<>();

    private static Scanner getScanner(GameStructure structure) {
        String path = "/gameStructures/" + structure.name() + ".cells";
        InputStream file = StructureReader.class.getResourceAsStream(path);
        return new Scanner(new InputStreamReader(file, StandardCharsets.UTF_8));
    }

    private static CellState getState(char c) {
        if (c == 'O') {
            return BinaryState.ALIVE;
        } else if (c == '.') {
            return BinaryState.DEAD;
        } else throw new IllegalArgumentException("illegal character in file");
    }

    public Map<Coords2D, CellState> getStructure(GameStructure structure, Coords2D startingPoint) {
        Scanner scanner = getScanner(structure);
        readFile(scanner);
        return translateStructure(startingPoint);
    }

    private Map<Coords2D, CellState> translateStructure(Coords2D startingPoint) {
        Map<Coords2D, CellState> translatedStructure = new HashMap<>();
        for (Map.Entry<Coords2D, CellState> cell : rawStructure.entrySet()) {
            int x = cell.getKey().getX() + startingPoint.getX();
            int y = cell.getKey().getY() + startingPoint.getY();
            if (x < widthOfBoard && y < heightOfBoard) {
                translatedStructure.put(new Coords2D(x, y), cell.getValue());
            }
        }
        return translatedStructure;
    }

    private void readFile(Scanner scanner) {
        int lineNumber = 0;
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.contains("!")) continue;
            readLine(line, lineNumber++);
        }
    }

    private void readLine(String line, int numberOfLine) {


        for (int x = 0; x < line.length(); x++) {
            rawStructure.put(new Coords2D(x, numberOfLine), getState(line.charAt(x)));
        }
    }

}
