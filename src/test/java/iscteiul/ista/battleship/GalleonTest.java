package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the Galleon class (Size 5), part of the S3 Ship Test Case.
 */
@DisplayName("C7 Galleon Test (Size 5)")
class GalleonTest {

    private final Position START_POS = new Position(5, 5);
    private Galleon galleon;

    @Test
    @DisplayName("Galleon: Check Size and Category")
    void testSizeAndCategory() {
        galleon = new Galleon(Compass.NORTH, START_POS);
        assertEquals("Galeao", galleon.getCategory());
        assertEquals(5, galleon.getSize(), "The size of the Galleon must be 5.");
    }

    @Test
    @DisplayName("Galleon: Positions (NORTH)")
    void testPositionsNorth() {
        galleon = new Galleon(Compass.NORTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(4, 5),
                new Position(3, 5),
                new Position(2, 5),
                new Position(1, 5)
        );
        assertTrue(galleon.getPositions().containsAll(expected) && expected.containsAll(galleon.getPositions()));
    }

    @Test
    @DisplayName("Galleon: Positions (SOUTH)")
    void testPositionsSouth() {
        galleon = new Galleon(Compass.SOUTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(6, 5),
                new Position(7, 5),
                new Position(8, 5),
                new Position(9, 5)
        );
        assertTrue(galleon.getPositions().containsAll(expected) && expected.containsAll(galleon.getPositions()));
    }

    @Test
    @DisplayName("Galleon: Positions (EAST)")
    void testPositionsEast() {
        galleon = new Galleon(Compass.EAST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 6),
                new Position(5, 7),
                new Position(5, 8),
                new Position(5, 9)
        );
        assertTrue(galleon.getPositions().containsAll(expected) && expected.containsAll(galleon.getPositions()));
    }

    @Test
    @DisplayName("Galleon: Positions (WEST)")
    void testPositionsWest() {
        galleon = new Galleon(Compass.WEST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 4),
                new Position(5, 3),
                new Position(5, 2),
                new Position(5, 1)
        );
        assertTrue(galleon.getPositions().containsAll(expected) && expected.containsAll(galleon.getPositions()));
    }
}