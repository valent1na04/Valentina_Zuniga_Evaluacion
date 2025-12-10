package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BulkDiscountPriceStrategy Unit Tests")
class BulkDiscountPriceStrategyTest {

    private BulkDiscountPriceStrategy strategy;
    private Mueble testMueble;
    private Variante testVariante;

    @BeforeEach
    void setUp() {
        strategy = new BulkDiscountPriceStrategy();
        testMueble = new Mueble("Silla", "Silla", 10000, 50, true, "Mediano", "Plástico");
        testVariante = new Variante("Color Especial", "Pintura especial", 2000);
    }

    @Test
    @DisplayName("Should not apply discount for less than 5 items")
    void testCalculatePrice_NoDiscount() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 4);

        // Assert
        assertEquals(40000, result); // 10000 * 4, no discount
    }

    @Test
    @DisplayName("Should apply 5% discount for 5 items")
    void testCalculatePrice_5PercentDiscount_ExactlyFive() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 5);

        // Assert
        // (10000 * 5) * 0.95 = 50000 * 0.95 = 47500
        assertEquals(47500, result);
    }

    @Test
    @DisplayName("Should apply 5% discount for 9 items")
    void testCalculatePrice_5PercentDiscount_Nine() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 9);

        // Assert
        // (10000 * 9) * 0.95 = 90000 * 0.95 = 85500
        assertEquals(85500, result);
    }

    @Test
    @DisplayName("Should apply 10% discount for 10 items")
    void testCalculatePrice_10PercentDiscount_ExactlyTen() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 10);

        // Assert
        // (10000 * 10) * 0.90 = 100000 * 0.90 = 90000
        assertEquals(90000, result);
    }

    @Test
    @DisplayName("Should apply 10% discount for 15 items")
    void testCalculatePrice_10PercentDiscount_Fifteen() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 15);

        // Assert
        // (10000 * 15) * 0.90 = 150000 * 0.90 = 135000
        assertEquals(135000, result);
    }

    @Test
    @DisplayName("Should apply 10% discount for 19 items")
    void testCalculatePrice_10PercentDiscount_Nineteen() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 19);

        // Assert
        // (10000 * 19) * 0.90 = 190000 * 0.90 = 171000
        assertEquals(171000, result);
    }

    @Test
    @DisplayName("Should apply 15% discount for 20 items")
    void testCalculatePrice_15PercentDiscount_ExactlyTwenty() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 20);

        // Assert
        // (10000 * 20) * 0.85 = 200000 * 0.85 = 170000
        assertEquals(170000, result);
    }

    @Test
    @DisplayName("Should apply 15% discount for 50 items")
    void testCalculatePrice_15PercentDiscount_Fifty() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, null, 50);

        // Assert
        // (10000 * 50) * 0.85 = 500000 * 0.85 = 425000
        assertEquals(425000, result);
    }

    @Test
    @DisplayName("Should apply discount with variant included")
    void testCalculatePrice_WithVariant_BulkDiscount() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, testVariante, 10);

        // Assert
        // ((10000 + 2000) * 10) * 0.90 = 120000 * 0.90 = 108000
        assertEquals(108000, result);
    }

    @Test
    @DisplayName("Should apply 15% discount with variant for 25 items")
    void testCalculatePrice_WithVariant_MaxDiscount() {
        // Act
        Integer result = strategy.calculatePrice(testMueble, testVariante, 25);

        // Assert
        // ((10000 + 2000) * 25) * 0.85 = 300000 * 0.85 = 255000
        assertEquals(255000, result);
    }

    @Test
    @DisplayName("Should throw exception when mueble is null")
    void testCalculatePrice_NullMueble() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(null, testVariante, 10);
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
    @DisplayName("Should throw exception when cantidad is zero or negative")
    void testCalculatePrice_InvalidCantidad() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(testMueble, testVariante, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(testMueble, testVariante, -5);
        });
    }
}
