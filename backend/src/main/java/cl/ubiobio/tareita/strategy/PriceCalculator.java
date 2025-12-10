package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.springframework.stereotype.Component;

/**
 * Context class for Strategy Pattern
 * Uses different price calculation strategies
 */
@Component
public class PriceCalculator {

    private PriceCalculationStrategy strategy;

    public PriceCalculator() {
        // Default strategy
        this.strategy = new StandardPriceStrategy();
    }

    public void setStrategy(PriceCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public Integer calculatePrice(Mueble mueble, Variante variante, Integer cantidad) {
        return strategy.calculatePrice(mueble, variante, cantidad);
    }
}
