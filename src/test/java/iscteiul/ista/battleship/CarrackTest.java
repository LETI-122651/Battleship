package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the Carrack class (Size 3), part of the S3 Ship Test Case.
 */
@DisplayName("C5 Carrack Test (Size 3)")
class CarrackTest {

    private final Position START_POS = new Position(5, 5);
    private Carrack carrack;

    @Test
    @DisplayName("Carrack: Check Size and Category")
    void testSizeAndCategory() {
        carrack = new Carrack(Compass.WEST, START_POS);
        assertEquals("Nau", carrack.getCategory());
        assertEquals(3, carrack.getSize(), "The size of the Carrack must be 3.");
    }

    @Test
    @DisplayName("Carrack: Positions (NORTH)")
    void testPositionsNorth() {
        carrack = new Carrack(Compass.NORTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(6, 5),
                new Position(7, 5)
        );
        assertTrue(carrack.getPositions().containsAll(expected) && expected.containsAll(carrack.getPositions()));
    }

    @Test
    @DisplayName("Carrack: Positions (SOUTH)")
    void testPositionsSouth() {
        carrack = new Carrack(Compass.SOUTH, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(6, 5),
                new Position(7, 5)
        );
        assertTrue(carrack.getPositions().containsAll(expected) && expected.containsAll(carrack.getPositions()));
    }

    @Test
    @DisplayName("Carrack: Positions (EAST)")
    void testPositionsEast() {
        carrack = new Carrack(Compass.EAST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 6),
                new Position(5, 7)
        );
        assertTrue(carrack.getPositions().containsAll(expected) && expected.containsAll(carrack.getPositions()));
    }

    @Test
    @DisplayName("Carrack: Positions (WEST)")
    void testPositionsWest() {
        carrack = new Carrack(Compass.WEST, START_POS);
        List<IPosition> expected = List.of(
                new Position(5, 5),
                new Position(5, 6),
                new Position(5, 7)
        );
        assertTrue(carrack.getPositions().containsAll(expected) && expected.containsAll(carrack.getPositions()));
    }
}