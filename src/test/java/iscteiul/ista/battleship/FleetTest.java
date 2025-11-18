package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import static iscteiul.ista.battleship.IFleet.BOARD_SIZE;
import static iscteiul.ista.battleship.IFleet.FLEET_SIZE;

@DisplayName("Testes à classe Fleet")
class FleetTest {

    private Fleet fleet;

    // navios base para os testes
    private IShip frigate;
    private IShip galleon;
    private IShip barge;

    // navios auxiliares para cenários específicos
    private IShip frigateToColide;
    private IShip outOfBoundsShip;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();

        // inicialização dos navios em posições válidas
        frigate = new Frigate(Compass.SOUTH, new Position(0, 0)); // tamanho 3
        galleon = new Galleon(Compass.EAST, new Position(7, 4)); // tamanho 5, válido
        barge = new Barge(Compass.SOUTH, new Position(5, 5)); // tamanho 1

        // navios com falhas
        frigateToColide = new Frigate(Compass.NORTH, new Position(0, 0)); // colide com o frigate
        outOfBoundsShip = new Galleon(Compass.EAST, new Position(0, 1)); // sai fora do tabuleiro
    }

    @Test
    @DisplayName("teste para adicionar navio (sucesso, colisão e limite de frota)")
    void testAddShipLifecycle() {
        // 1. sucesso na adição
        assertTrue(fleet.addShip(frigate), "deve adicionar o primeiro navio (Fragata) com sucesso");
        assertTrue(fleet.addShip(barge), "deve adicionar o segundo navio (Barca) com sucesso");
        assertEquals(2, fleet.getShips().size(), "a frota deve ter 2 navios");

        // 2. colisão/proximidade (falha)
        assertFalse(fleet.addShip(frigateToColide), "não deve adicionar devido a colisão");
        assertEquals(2, fleet.getShips().size(), "a frota deve continuar com 2 navios após a colisão");

        // 3. fora do tabuleiro (falha) - teste de limites básicos
        assertFalse(fleet.addShip(outOfBoundsShip), "não deve adicionar navio que sai dos limites");
        assertEquals(2, fleet.getShips().size(), "a frota deve continuar com 2 navios");

        // 4. limite da frota (falha)
        Fleet fleetToFill = new Fleet();
        // preenche a frota com 11 barcas (tamanho 1) para atingir FLEET_SIZE
        Position[] positions = {
                new Position(0,0), new Position(0,2), new Position(0,4),
                new Position(2,0), new Position(2,2), new Position(2,4),
                new Position(4,0), new Position(4,2), new Position(4,4),
                new Position(6,0), new Position(6,2)
        };

        for (Position pos : positions) {
            assertTrue(fleetToFill.addShip(new Barge(Compass.NORTH, pos)), "deve adicionar uma das 11 barcas");
        }
        assertEquals(FLEET_SIZE+1, fleetToFill.getShips().size(), "atingiu o limite de navios");

        // tenta adicionar o 12º navio
        IShip extraShip = new Barge(Compass.SOUTH, new Position(8, 8));
        assertFalse(fleetToFill.addShip(extraShip), "não deve adicionar o navio após atingir o limite");
    }

    @Test
    @DisplayName("teste para o método que verifica limites do tabuleiro")
    void testIsInsideBoardBranches() {
        // fora à esquerda (Col < 0)
        IShip left = new Frigate(Compass.NORTH, new Position(0, -1));
        assertFalse(fleet.addShip(left), "deve falhar: Coluna < 0");

        // fora à direita (Col > BOARD_SIZE - 1)
        IShip right = new Frigate(Compass.EAST, new Position(0, BOARD_SIZE - 1)); // Fragata tem 3 de tamanho, vai de 9 a 11
        assertFalse(fleet.addShip(right), "deve falhar: Coluna > Max");

        // fora acima (Row < 0)
        IShip top = new Frigate(Compass.NORTH, new Position(-1, 0));
        assertFalse(fleet.addShip(top), "deve falhar: Linha < 0");

        // fora abaixo (Row > BOARD_SIZE - 1)
        IShip bottom = new Frigate(Compass.SOUTH, new Position(BOARD_SIZE - 1, 0)); // Fragata tem 3 de tamanho, vai de 9 a 11
        assertFalse(fleet.addShip(bottom), "deve falhar: Linha > Max");

        // dentro do tabuleiro
        IShip valid = new Frigate(Compass.NORTH, new Position(5, 5));
        assertTrue(fleet.addShip(valid), "deve ter sucesso: posição válida.");
    }

    @Test
    @DisplayName("teste para obter navios por categoria")
    void testGetShipsLike() {
        fleet.addShip(frigate);
        fleet.addShip(galleon);
        fleet.addShip(barge);

        // testa categoria existente
        List<IShip> fragates = fleet.getShipsLike("Fragata");
        assertEquals(1, fragates.size());
        assertEquals(frigate, fragates.get(0));

        // testa categoria não existente
        List<IShip> nonExistent = fleet.getShipsLike("Nau");
        assertTrue(nonExistent.isEmpty());
    }

    @Test
    @DisplayName("teste para obter os navios flutuantes")
    void testGetFloatingShips() {
        fleet.addShip(frigate);
        fleet.addShip(barge);

        // o 'barge' (tamanho 1) está em (5, 5), atira
        IPosition bargePos = barge.getPositions().get(0);
        barge.shoot(bargePos); // afunda a barca

        List<IShip> floatingShips = fleet.getFloatingShips();
        assertEquals(1, floatingShips.size());
        assertTrue(floatingShips.contains(frigate), "a fragata deve estar na lista (ainda flutua)");
        assertFalse(floatingShips.contains(barge), "a barca afundada não deve estar na lista");
    }

    @Test
    @DisplayName("teste para obter o navio numa dada posição")
    void testShipAt() {
        fleet.addShip(frigate); // fragata em (0,0) SUL, ocupa (0,0), (1,0), (2,0)

        // ocupada
        IPosition posOccupied = new Position(1, 0);
        assertEquals(frigate, fleet.shipAt(posOccupied), "deve encontrar a fragata na posição (1,0)");

        // não ocupada
        IPosition emptyPos = new Position(9, 9);
        assertNull(fleet.shipAt(emptyPos), "não deve encontrar navio na posição vazia (9,9)");
    }

    @Test
    @DisplayName("teste para os métodos de impressão e status")
    void testPrintMethodsCoverage() {
        fleet.addShip(frigate);
        fleet.addShip(galleon);
        fleet.addShip(barge);

        assertDoesNotThrow(() -> fleet.printStatus(), "printStatus não deve lançar exceções");

        assertThrows(AssertionError.class, () -> {
            fleet.printShipsByCategory(null);
        }, "lança AssertionError se a categoria for nula");

        assertDoesNotThrow(() -> Fleet.printShips(fleet.getShips()), "printShips (estático) não deve lançar exceções");
    }
}