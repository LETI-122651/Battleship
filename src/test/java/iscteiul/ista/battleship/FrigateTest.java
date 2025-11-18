package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the Frigate class (Size 4), part of the S3 Ship Test Case.
 */
@DisplayName("C6 Frigate Test (Size 4)")
class FrigateTest {

    private final Position START_POS = new Position(5, 5);
    private Frigate frigate;

    @Test
    @DisplayName("Frigate: Check Size and Category")
    void testSizeAndCategory() {
        frigate = new Frigate(Compass.NORTH, START_POS);
        assertEquals("Fragata", frigate.getCategory());
        assertEquals(4, frigate.getSize(), "The size of the Frigate must be 4.");
    }

    @Test
    @DisplayName("Frigate: Positions (NORTH)")
    void testPositionsNorth() {
        frigate = new Frigate(Compass.NORTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(6, 5),
                new Position(7, 5),
                new Position(8, 5)
        );
        assertTrue(frigate.getPositions().containsAll(expected) && expected.containsAll(frigate.getPositions()));
    }

    @Test
    @DisplayName("Frigate: Positions (SOUTH)")
    void testPositionsSouth() {
        frigate = new Frigate(Compass.SOUTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(6, 5),
                new Position(7, 5),
                new Position(8, 5)
        );
        assertTrue(frigate.getPositions().containsAll(expected) && expected.containsAll(frigate.getPositions()));
    }

    @Test
    @DisplayName("Frigate: Positions (EAST)")
    void testPositionsEast() {
        frigate = new Frigate(Compass.EAST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 6),
                new Position(5, 7),
                new Position(5, 8)
        );
        assertTrue(frigate.getPositions().containsAll(expected) && expected.containsAll(frigate.getPositions()));
    }

    @Test
    @DisplayName("Frigate: Positions (WEST)")
    void testPositionsWest() {
        frigate = new Frigate(Compass.WEST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 6),
                new Position(5, 7),
                new Position(5, 8)
        );
        assertTrue(frigate.getPositions().containsAll(expected) && expected.containsAll(frigate.getPositions()));
    }
}