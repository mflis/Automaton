package pl.agh.edu.Automaton.model.neighbourhood;

import pl.agh.edu.Automaton.model.coordinates.CellCoordinates;
import pl.agh.edu.Automaton.model.coordinates.Coords2D;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * represents Von Neumann  neighborhood strategy of searching neighbours.
 * When distance=1 then neighbours are  the four cells orthogonally
 * surrounding a central cell on a two-dimensional square lattice.
 * <p>
 * An extension of the simple von Neumann neighborhood described above
 * is to take the set of points at a Manhattan distance of r &gt; 1.
 * This results in a diamond-shaped region
 */
public class VonNeumannNeighbourhood extends NeighbourHood2D {

    /**
     * @see NeighbourHood2D#NeighbourHood2D(int, int, int, Wrapping)
     */
    public VonNeumannNeighbourhood(int distance, int width, int height, Wrapping wrapping) {
        super(distance, width, height, wrapping);
    }

    @Override
    public Set<CellCoordinates> cellNeighbours(CellCoordinates cell) {
        Set<CellCoordinates> result = new LinkedHashSet<>();
        Coords2D center = (Coords2D) cell;
        int minX = center.getX() - distance;
        int maxX = center.getX() + distance;
        int minY = center.getY() - distance;
        int maxY = center.getY() + distance;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Coords2D current = new Coords2D(x, y);
                if (center.equals(current)) continue;
                if (!isVonNeumanNeighbour(current, center)) continue;

                if (isOutside(x, y)) {
                    if (wrapping == Wrapping.ON) {
                        current = getWrappedCoords2D(x, y);
                    } else current = null;
                }
                if (current != null) {
                    result.add(current);
                }
            }
        }
        return result;
    }

    private boolean isVonNeumanNeighbour(Coords2D current, Coords2D center) {
        return (Math.abs(current.getX() - center.getX()) + Math.abs(current.getY() - center.getY())) <= distance;
    }


}
