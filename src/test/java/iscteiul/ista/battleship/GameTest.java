package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Testes da classe Game")
class GameTest {

    private Game game;
    private Fleet fleet;

    private Position p00, p01, p02;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();

        Ship caravel = new Caravel(Compass.EAST, new Position(0, 0)); // ocupa (0,0) e (0,1)
        fleet.addShip(caravel);

        game = new Game(fleet);

        p00 = new Position(0, 0); // acerto
        p01 = new Position(0, 1); // acerto
        p02 = new Position(0, 2); // falha
    }

    @AfterEach
    void tearDown() {
        game = null;
        fleet = null;
    }

    @Nested
    @DisplayName("Testes do método fire()")
    class FireTests {

        @Test
        @DisplayName("tiro inválido incrementa contador de invalidShots")
        void fireInvalidShot() {
            Position invalid = new Position(-1, 0);

            IShip result = game.fire(invalid);

            assertNull(result);
            assertEquals(1, game.getInvalidShots());
        }

        @Test
        @DisplayName("tiro repetido incrementa contador de repeatedShots")
        void fireRepeatedShot() {
            game.fire(p02);
            game.fire(p02);

            assertEquals(1, game.getRepeatedShots());
        }

        @Test
        @DisplayName("tiro falhado não incrementa hits nem sinks")
        void fireMiss() {
            IShip result = game.fire(p02);

            assertNull(result);
            assertEquals(0, game.getHits());
            assertEquals(0, game.getSunkShips());
        }

        @Test
        @DisplayName("tiro que acerta incrementa os hits e não afunda de imediato")
        void fireHit() {
            IShip result = game.fire(p00);

            assertNull(result);
            assertEquals(1, game.getHits());
        }

        @Test
        @DisplayName("tiro que afunda barco devolve o navio e incrementa sinks")
        void fireSinkShip() {
            game.fire(p00);
            IShip sunk = game.fire(p01);

            assertNotNull(sunk);
            assertEquals(1, game.getSunkShips());
        }
    }

    @Nested
    @DisplayName("testes dos contadores e listas de estado")
    class StateTests {

        @Test
        @DisplayName("retorna todas as posições de tiros válidos e não repetidos")
        void getShots() {
            game.fire(p02);
            game.fire(p00);

            List<IPosition> shots = game.getShots();

            assertEquals(2, shots.size());
            assertTrue(shots.contains(p02));
            assertTrue(shots.contains(p00));
        }

        @Test
        @DisplayName("incrementa corretamente")
        void repeatedShots() {
            game.fire(p02);
            game.fire(p02);

            assertEquals(1, game.getRepeatedShots());
        }

        @Test
        @DisplayName("incrementa corretamente")
        void invalidShots() {
            game.fire(new Position(-1, -1));
            game.fire(new Position(100, 100));

            assertEquals(2, game.getInvalidShots());
        }

        @Test
        @DisplayName("incrementa corretamente após acertos")
        void getHits() {
            game.fire(p00);
            game.fire(p01);

            assertEquals(2, game.getHits());
        }

        @Test
        @DisplayName("conta adequadamente")
        void getSunkShips() {
            game.fire(p00);
            game.fire(p01);

            assertEquals(1, game.getSunkShips());
        }

        @Test
        @DisplayName("diminui após afundamento")
        void getRemainingShips() {
            assertEquals(1, game.getRemainingShips());

            game.fire(p00);
            game.fire(p01);

            assertEquals(0, game.getRemainingShips());
        }
    }

    @Nested
    @DisplayName("testes de impressão na consola")
    class PrintTests {

        @Test
        @DisplayName("não deve lançar exceções")
        void printBoard() {
            List<IPosition> pos = new ArrayList<>();
            pos.add(p00);

            assertDoesNotThrow(() -> game.printBoard(pos, 'X'));
        }

        @Test
        @DisplayName("não deve lançar exceções")
        void printValidShots() {
            game.fire(p02);
            assertDoesNotThrow(() -> game.printValidShots());
        }

        @Test
        @DisplayName("não deve lançar exceções")
        void printFleet() {
            assertDoesNotThrow(() -> game.printFleet());
        }
    }
}