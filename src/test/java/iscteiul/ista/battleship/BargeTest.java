package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the Barge class (Size 1), part of the S3 Ship Test Case.
 */
@DisplayName("C3 Barge Test (Size 1)")
class BargeTest {

    private final Position START_POS = new Position(5, 5);
    private Barge barge;

    @BeforeEach
    void setUp() {
        barge = new Barge(Compass.NORTH, START_POS);
    }

    @Test
    @DisplayName("Barge: Check Size and Category")
    void testSizeAndCategory() {
        assertEquals("Barca", barge.getCategory());
        assertEquals(1, barge.getSize(), "The size of the Barge must be 1.");
    }

    @Test
    @DisplayName("Barge: Check Generated Positions (Size 1)")
    void testPositions() {
        List<IPosition> positions = barge.getPositions();
        assertEquals(1, positions.size(), "Must have only 1 position.");
        assertTrue(positions.contains(START_POS));
    }
}