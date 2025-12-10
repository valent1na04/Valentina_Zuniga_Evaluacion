package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PremiumPriceStrategy Unit Tests")
class PremiumPriceStrategyTest {

    private PremiumPriceStrategy strategy;
    private Variante testVariante;

    @BeforeEach
    void setUp() {
        strategy = new PremiumPriceStrategy();
        testVariante = new Variante("Acabado Premium", "Acabado especial", 10000);
    }

    @Test
    @DisplayName("Should apply premium markup for Cuero material")
    void testCalculatePrice_PremiumMaterial_Cuero() {
        // Arrange
        Mueble mueble = new Mueble("Sofa", "Sofa", 100000, 5, true, "Grande", "Cuero");

        // Act
        Integer result = strategy.calculatePrice(mueble, null, 1);

        // Assert
        // (100000 * 1.20) * 1 = 120000
        assertEquals(120000, result);
    }

    @Test
    @DisplayName("Should apply premium markup for Roble material")
    void testCalculatePrice_PremiumMaterial_Roble() {
        // Arrange
        Mueble mueble = new Mueble("Mesa", "Mesa", 100000, 5, true, "Grande", "Roble");

        // Act
        Integer result = strategy.calculatePrice(mueble, null, 2);

        // Assert
        // (100000 * 1.20) * 2 = 240000
        assertEquals(240000, result);
    }

    @Test
    @DisplayName("Should apply premium markup for Mármol material")
    void testCalculatePrice_PremiumMaterial_Marmol() {
        // Arrange
        Mueble mueble = new Mueble("Mesa", "Mesa", 100000, 5, true, "Grande", "Mármol");

        // Act
        Integer result = strategy.calculatePrice(mueble, null, 1);

        // Assert
        assertEquals(120000, result);
    }

    @Test
    @DisplayName("Should apply premium markup for Caoba material")
    void testCalculatePrice_PremiumMaterial_Caoba() {
        // Arrange
        Mueble mueble = new Mueble("Escritorio", "Mesa", 100000, 5, true, "Grande", "Caoba");

        // Act
        Integer result = strategy.calculatePrice(mueble, null, 1);

        // Assert
        assertEquals(120000, result);
    }

    @Test
    @DisplayName("Should not apply premium markup for regular material")
    void testCalculatePrice_RegularMaterial() {
        // Arrange
        Mueble mueble = new Mueble("Silla", "Silla", 100000, 10, true, "Mediano", "Plástico");

        // Act
        Integer result = strategy.calculatePrice(mueble, null, 2);

        // Assert
        // No premium markup, 100000 * 2 = 200000
        assertEquals(200000, result);
    }

    @Test
    @DisplayName("Should apply premium markup with variant")
    void testCalculatePrice_PremiumMaterialWithVariant() {
        // Arrange
        Mueble mueble = new Mueble("Sofa", "Sofa", 100000, 5, true, "Grande", "Cuero Premium");

        // Act
        Integer result = strategy.calculatePrice(mueble, testVariante, 2);

        // Assert
        // ((100000 + 10000) * 1.20) * 2 = 132000 * 2 = 264000
        assertEquals(264000, result);
    }

    @Test
    @DisplayName("Should handle case insensitive material matching")
    void testCalculatePrice_CaseInsensitive() {
        // Arrange
        Mueble mueble = new Mueble("Mesa", "Mesa", 100000, 5, true, "Grande", "cuero italiano");

        // Act
        Integer result = strategy.calculatePrice(mueble, null, 1);

        // Assert
        assertEquals(120000, result);
    }

    @Test
    @DisplayName("Should handle null material as regular material")
    void testCalculatePrice_NullMaterial() {
        // Arrange
        Mueble mueble = new Mueble("Silla", "Silla", 100000, 10, true, "Mediano", null);

        // Act
        Integer result = strategy.calculatePrice(mueble, null, 1);

        // Assert
        assertEquals(100000, result);
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
    @DisplayName("Should throw exception when cantidad is invalid")
    void testCalculatePrice_InvalidCantidad() {
        // Arrange
        Mueble mueble = new Mueble("Mesa", "Mesa", 100000, 5, true, "Grande", "Cuero");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(mueble, testVariante, 0);
        });
    }
}
