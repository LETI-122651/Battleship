package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes ao enum Compass")
class CompassTest {

    @Test
    @DisplayName("devolve o caracter associado a cada direção")
    void getDirection() {
        assertAll("direções",
                () -> assertEquals('n', Compass.NORTH.getDirection()),
                () -> assertEquals('s', Compass.SOUTH.getDirection()),
                () -> assertEquals('e', Compass.EAST.getDirection()),
                () -> assertEquals('o', Compass.WEST.getDirection()),
                () -> assertEquals('u', Compass.UNKNOWN.getDirection())
        );
    }

    @Test
    @DisplayName("devolve o caracter da direção")
    void testToString() {
        assertAll("toString",
                () -> assertEquals("n", Compass.NORTH.toString()),
                () -> assertEquals("s", Compass.SOUTH.toString()),
                () -> assertEquals("e", Compass.EAST.toString()),
                () -> assertEquals("o", Compass.WEST.toString()),
                () -> assertEquals("u", Compass.UNKNOWN.toString())
        );
    }

    @Test
    @DisplayName("converte caracteres válidos e inválidos")
    void charToCompass() {
        assertAll("charToCompass",
                () -> assertEquals(Compass.NORTH, Compass.charToCompass('n')),
                () -> assertEquals(Compass.SOUTH, Compass.charToCompass('s')),
                () -> assertEquals(Compass.EAST, Compass.charToCompass('e')),
                () -> assertEquals(Compass.WEST, Compass.charToCompass('o')),
                () -> assertEquals(Compass.UNKNOWN, Compass.charToCompass('a')),
                () -> assertEquals(Compass.UNKNOWN, Compass.charToCompass(' '))
        );
    }
}