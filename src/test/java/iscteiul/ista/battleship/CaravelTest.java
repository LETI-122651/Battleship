package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the Caravel class (Size 2), part of the S3 Ship Test Case.
 */
@DisplayName("C4 Caravel Test (Size 2)")
class CaravelTest {

    private final Position START_POS = new Position(5, 5);

    @Test
    @DisplayName("Caravel: Check Size and Category")
    void testSizeAndCategory() {
        Caravel caravel = new Caravel(Compass.EAST, START_POS);
        assertEquals("Caravela", caravel.getCategory());
        assertEquals(2, caravel.getSize(), "The size of the Caravel must be 2.");
    }

    @Test
    @DisplayName("Caravel: Positions (NORTH)")
    void testPositionsNorth() {
        Caravel caravel = new Caravel(Compass.NORTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(6, 5)
        );
        assertTrue(caravel.getPositions().containsAll(expected) && expected.containsAll(caravel.getPositions()));
    }

    @Test
    @DisplayName("Caravel: Positions (SOUTH)")
    void testPositionsSouth() {
        Caravel caravel = new Caravel(Compass.SOUTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(6, 5)
        );
        assertTrue(caravel.getPositions().containsAll(expected) && expected.containsAll(caravel.getPositions()));
    }

    @Test
    @DisplayName("Caravel: Positions (EAST)")
    void testPositionsEast() {
        Caravel caravel = new Caravel(Compass.EAST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 6)
        );
        assertTrue(caravel.getPositions().containsAll(expected) && expected.containsAll(caravel.getPositions()));
    }

    @Test
    @DisplayName("Caravel: Positions (WEST)")
    void testPositionsWest() {
        Caravel caravel = new Caravel(Compass.WEST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 6)
        );
        assertTrue(caravel.getPositions().containsAll(expected) && expected.containsAll(caravel.getPositions()));
    }

    @Test
    @DisplayName("Caravel: Null Bearing Check")
    void testNullBearing() {
        assertThrows(AssertionError.class, () -> new Caravel(null, START_POS));
    }

    @Test
    @DisplayName("Caravel: Unhandled Bearing Check (Default Branch)")
    void testUnhandledBearing() {
        assertThrows(IllegalArgumentException.class, () -> new Caravel(Compass.UNKNOWN, START_POS));
    }
}