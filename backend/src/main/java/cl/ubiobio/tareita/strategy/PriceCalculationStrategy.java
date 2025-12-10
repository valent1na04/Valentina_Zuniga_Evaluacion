package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;

/**
 * Strategy Pattern: Interface for different price calculation strategies
 */
public interface PriceCalculationStrategy {
    Integer calculatePrice(Mueble mueble, Variante variante, Integer cantidad);
}
