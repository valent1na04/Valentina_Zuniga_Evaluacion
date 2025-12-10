package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PriceCalculator Unit Tests")
class PriceCalculatorTest {

    private PriceCalculator priceCalculator;
    private Mueble testMueble;
    private Variante testVariante;

    @BeforeEach
    void setUp() {
        priceCalculator = new PriceCalculator();
        testMueble = new Mueble("Mesa", "Mesa", 100000, 10, true, "Grande", "Madera");
        testVariante = new Variante("Acabado", "Acabado especial", 10000);
    }

    @Test
    @DisplayName("Should use default StandardPriceStrategy")
    void testDefaultStrategy() {
        // Act
        Integer result = priceCalculator.calculatePrice(testMueble, null, 2);

        // Assert
        // Standard strategy: 100000 * 2 = 200000
        assertEquals(200000, result);
    }

    @Test
    @DisplayName("Should allow changing strategy to PremiumPriceStrategy")
    void testChangeToPreemiumStrategy() {
        // Arrange
        Mueble premiumMueble = new Mueble("Sofa", "Sofa", 100000, 5, true, "Grande", "Cuero");
        priceCalculator.setStrategy(new PremiumPriceStrategy());

        // Act
        Integer result = priceCalculator.calculatePrice(premiumMueble, null, 1);

        // Assert
        // Premium strategy with Cuero material: 100000 * 1.20 = 120000
        assertEquals(120000, result);
    }

    @Test
    @DisplayName("Should allow changing strategy to BulkDiscountPriceStrategy")
    void testChangeToBulkDiscountStrategy() {
        // Arrange
        priceCalculator.setStrategy(new BulkDiscountPriceStrategy());

        // Act
        Integer result = priceCalculator.calculatePrice(testMueble, null, 10);

        // Assert
        // Bulk discount strategy: (100000 * 10) * 0.90 = 900000
        assertEquals(900000, result);
    }

    @Test
    @DisplayName("Should switch between strategies dynamically")
    void testDynamicStrategySwitch() {
        // Test with Standard Strategy
        Integer standardResult = priceCalculator.calculatePrice(testMueble, null, 5);
        assertEquals(500000, standardResult);

        // Switch to Bulk Discount Strategy
        priceCalculator.setStrategy(new BulkDiscountPriceStrategy());
        Integer bulkResult = priceCalculator.calculatePrice(testMueble, null, 5);
        assertEquals(475000, bulkResult); // 500000 * 0.95

        // Switch to Premium Strategy
        Mueble premiumMueble = new Mueble("Sofa", "Sofa", 100000, 5, true, "Grande", "Roble");
        priceCalculator.setStrategy(new PremiumPriceStrategy());
        Integer premiumResult = priceCalculator.calculatePrice(premiumMueble, null, 1);
        assertEquals(120000, premiumResult); // 100000 * 1.20
    }

    @Test
    @DisplayName("Should work with variant using different strategies")
    void testVariantWithDifferentStrategies() {
        // Standard Strategy
        Integer standardResult = priceCalculator.calculatePrice(testMueble, testVariante, 2);
        assertEquals(220000, standardResult); // (100000 + 10000) * 2

        // Bulk Discount Strategy
        priceCalculator.setStrategy(new BulkDiscountPriceStrategy());
        Integer bulkResult = priceCalculator.calculatePrice(testMueble, testVariante, 10);
        assertEquals(990000, bulkResult); // ((100000 + 10000) * 10) * 0.90

        // Premium Strategy with premium material
        Mueble premiumMueble = new Mueble("Escritorio", "Mesa", 100000, 5, true, "Grande", "Caoba");
        priceCalculator.setStrategy(new PremiumPriceStrategy());
        Integer premiumResult = priceCalculator.calculatePrice(premiumMueble, testVariante, 1);
        assertEquals(132000, premiumResult); // (100000 + 10000) * 1.20
    }

    @Test
    @DisplayName("Should maintain strategy state across multiple calculations")
    void testStrategyStatePersistence() {
        // Set to Bulk Discount Strategy
        priceCalculator.setStrategy(new BulkDiscountPriceStrategy());

        // First calculation
        Integer result1 = priceCalculator.calculatePrice(testMueble, null, 20);
        assertEquals(1700000, result1); // (100000 * 20) * 0.85

        // Second calculation with same strategy
        Integer result2 = priceCalculator.calculatePrice(testMueble, null, 5);
        assertEquals(475000, result2); // (100000 * 5) * 0.95

        // Strategy should still be BulkDiscount
        Integer result3 = priceCalculator.calculatePrice(testMueble, null, 10);
        assertEquals(900000, result3); // (100000 * 10) * 0.90
    }
}
