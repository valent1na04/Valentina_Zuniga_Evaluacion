package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StandardPriceStrategy Unit Tests")
class StandardPriceStrategyTest {

    private StandardPriceStrategy strategy;
    private Mueble testMueble;
    private Variante testVariante;

    @BeforeEach
    void setUp() {
        strategy = new StandardPriceStrategy();
        testMueble = new Mueble("Mesa", "Mesa", 100000, 10, true, "Mediano", "Madera");
        testVariante = new Variante("Acabado Premium", "Acabado especial", 20000);
    }

    @Test
    @DisplayName("Should calculate price without variant")
    void testCalculatePrice_WithoutVariant() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 2);

        // Assert
        assertEquals(200000, result); // 100000 * 2
    }

    @Test
    @DisplayName("Should calculate price with variant")
    void testCalculatePrice_WithVariant() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, testVariante, 3);

        // Assert
        assertEquals(360000, result); // (100000 + 20000) * 3
    }

    @Test
    @DisplayName("Should calculate price for single item")
    void testCalculatePrice_SingleItem() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, testVariante, 1);

        // Assert
        assertEquals(120000, result); // (100000 + 20000) * 1
    }

    @Test
    @DisplayName("Should throw exception when mueble is null")
    void testCalculatePrice_NullMueble() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(null, testVariante, 2);
        });
        assertEquals("Mueble y cantidad son requeridos", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cantidad is null")
    void testCalculatePrice_NullCantidad() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(testMueble, testVariante, null);
        });
        assertEquals("Mueble y cantidad son requeridos", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cantidad is zero")
    void testCalculatePrice_ZeroCantidad() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(testMueble, testVariante, 0);
        });
        assertEquals("Mueble y cantidad son requeridos", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when cantidad is negative")
    void testCalculatePrice_NegativeCantidad() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(testMueble, testVariante, -5);
        });
        assertEquals("Mueble y cantidad son requeridos", exception.getMessage());
    }
}
