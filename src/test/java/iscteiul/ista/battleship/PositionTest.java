package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes à classe Position")
class PositionTest {

    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 7);
    }

    @AfterEach
    void tearDown() {
        position = null;
    }

    @Test
    @DisplayName("deve retornar a linha correta")
    void getRow() {
        assertEquals(5, position.getRow(), "a linha deve ser 5");
    }

    @Test
    @DisplayName("deve retornar a coluna correta")
    void getColumn() {
        assertEquals(7, position.getColumn(), "a coluna deve ser 7");
    }

    @Test
    @DisplayName("gera hashCode consistente para posições iguais")
    void testHashCode() {
        Position same = new Position(5, 7);
        assertEquals(position.hashCode(), same.hashCode(),
                "hashCodes devem ser iguais para posições iguais");
    }

    @Test
    @DisplayName("Deve comparar igualdade corretamente")
    void testEquals() {
        Position same = new Position(5, 7);
        Position differentRow = new Position(4, 7);
        Position differentColumn = new Position(5, 8);

        assertAll("Testes de igualdade",
                () -> assertTrue(position.equals(same),
                        "Mesmas coordenadas devem ser iguais"),
                () -> assertFalse(position.equals(differentRow),
                        "Linhas diferentes não devem ser iguais"),
                () -> assertFalse(position.equals(differentColumn),
                        "Colunas diferentes não devem ser iguais"),
                () -> assertFalse(position.equals(null),
                        "Não deve ser igual a null"),
                () -> assertFalse(position.equals("Texto"),
                        "Não deve ser igual a outro tipo"),
                () -> assertTrue(position.equals(position),
                        "Um objeto deve ser igual a si próprio")
        );
    }

    @Test
    @DisplayName("Deve identificar posições adjacentes corretamente")
    void isAdjacentTo() {
        Position adjacent = new Position(6, 7);     // abaixo
        Position diagonal = new Position(4, 6);     // diagonal
        Position farAway = new Position(10, 10);
        Position farRow = new Position(12, 7);      // linha distante
        Position farColumn = new Position(5, 20);   // coluna distante

        assertAll("Testes de adjacência",
                () -> assertTrue(position.isAdjacentTo(adjacent),
                        "Posição imediatamente abaixo é adjacente"),
                () -> assertTrue(position.isAdjacentTo(diagonal),
                        "Posição diagonal é adjacente"),
                () -> assertFalse(position.isAdjacentTo(farAway),
                        "Posição distante não é adjacente"),
                () -> assertFalse(position.isAdjacentTo(farRow),
                        "Linhas distantes não são adjacentes"),
                () -> assertFalse(position.isAdjacentTo(farColumn),
                        "Colunas distantes não são adjacentes")
        );
    }

    @Test
    @DisplayName("deve marcar a posição como ocupada após occupy()")
    void occupy() {
        assertFalse(position.isOccupied(), "inicialmente está ocupada");
        position.occupy();
        assertTrue(position.isOccupied(), "após ocupar deve estar marcada como ocupada");
    }

    @Test
    @DisplayName("deve marcar a posição como atingida após shoot()")
    void shoot() {
        assertFalse(position.isHit(), "inicialmente não deve estar atingida");
        position.shoot();
        assertTrue(position.isHit(), "após shoot() deve estar marcada como atingida");
    }

    @Test
    @DisplayName("retorna corretamente o estado de ocupação")
    void isOccupied() {
        assertFalse(position.isOccupied());
        position.occupy();
        assertTrue(position.isOccupied());
    }

    @Test
    @DisplayName("retorna corretamente o estado de impacto")
    void isHit() {
        assertFalse(position.isHit());
        position.shoot();
        assertTrue(position.isHit());
    }

    @Test
    @DisplayName("retorna string formatada corretamente")
    void testToString() {
        String expected = "Linha = 5 Coluna = 7";
        assertEquals(expected, position.toString());
    }
}