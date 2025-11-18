package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Ship Abstract Class (Generic Ship Concept)")
class ShipTest {

    private Barge barge1;
    private Position pos1;

    @BeforeEach
    void setUp() {
        pos1 = new Position(5, 5);
        barge1 = new Barge(Compass.NORTH, pos1);
    }

    @Test
    @DisplayName("Test Constructor and Basic Getters")
    void testConstructorAndGetters() {
        String BARCA = "Barca";
        assertEquals(BARCA, barge1.getCategory());
        assertEquals(Compass.NORTH, barge1.getBearing());
        assertEquals(pos1, barge1.getPosition());
        assertNotNull(barge1.getPositions());
        assertEquals(1, barge1.getPositions().size());
        assertEquals(pos1, barge1.getPositions().get(0));
    }

    @Test
    @DisplayName("Test static buildShip constructor (switch coverage)")
    void testBuildShip() {
        IShip barca = Ship.buildShip("barca", Compass.EAST, new Position(0, 0));
        assertNotNull(barca);
        assertInstanceOf(Barge.class, barca);

        IShip nau = Ship.buildShip("nau", Compass.SOUTH, new Position(1, 1));
        assertNotNull(nau);
        assertInstanceOf(Carrack.class, nau);

        IShip unknown = Ship.buildShip("unknown", Compass.NORTH, new Position(0, 0));
        assertNull(unknown);
    }

    @Test
    @DisplayName("Test stillFloating and shoot method")
    void testStillFloatingAndShoot() {
        assertTrue(barge1.stillFloating());

        barge1.shoot(pos1);

        assertFalse(barge1.stillFloating());

        IPosition nonOccupiedPos = new Position(0, 0);
        assertDoesNotThrow(() -> barge1.shoot(nonOccupiedPos));
        assertFalse(nonOccupiedPos.isHit());
    }

    @Test
    @DisplayName("Test Boundary methods (getTopMostPos, etc.) with multi-position ship")
    void testBoundaryMethods() {
        Frigate frigateSouth = new Frigate(Compass.SOUTH, new Position(0, 0));

        assertEquals(0, frigateSouth.getTopMostPos());
        assertEquals(3, frigateSouth.getBottomMostPos());
        assertEquals(0, frigateSouth.getLeftMostPos());
        assertEquals(0, frigateSouth.getRightMostPos());

        Frigate frigateEast = new Frigate(Compass.EAST, new Position(5, 5));

        assertEquals(5, frigateEast.getTopMostPos());
        assertEquals(5, frigateEast.getBottomMostPos());
        assertEquals(5, frigateEast.getLeftMostPos());
        assertEquals(8, frigateEast.getRightMostPos());
    }

    @Test
    @DisplayName("Test occupies method")
    void testOccupies() {
        assertTrue(barge1.occupies(pos1));
        assertFalse(barge1.occupies(new Position(0, 0)));
    }

    @Test
    @DisplayName("Test tooCloseTo (IShip) method")
    void testTooCloseToShip() {
        IShip barge2 = new Barge(Compass.SOUTH, new Position(6, 6));
        assertTrue(barge1.tooCloseTo(barge2));

        IShip barge3 = new Barge(Compass.SOUTH, new Position(8, 8));
        assertFalse(barge1.tooCloseTo(barge3));
    }

    @Test
    @DisplayName("Test tooCloseTo (IPosition) method")
    void testTooCloseToPosition() {
        assertTrue(barge1.tooCloseTo(new Position(5, 6)));
        assertFalse(barge1.tooCloseTo(new Position(6, 7)));
        assertTrue(barge1.tooCloseTo(pos1));
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String expected = "[Barca n Linha = 5 Coluna = 5]";
        assertEquals(expected, barge1.toString());
    }
}