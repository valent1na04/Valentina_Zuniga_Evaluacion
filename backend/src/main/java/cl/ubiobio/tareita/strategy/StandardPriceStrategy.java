package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.springframework.stereotype.Component;

/**
 * Strategy Pattern: Standard price calculation
 * Price = (Base Price + Variant Increase) * Quantity
 */
@Component
public class StandardPriceStrategy implements PriceCalculationStrategy {

    @Override
    public Integer calculatePrice(Mueble mueble, Variante variante, Integer cantidad) {
        if (mueble == null || cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("Mueble y cantidad son requeridos");
        }

        Integer precioBase = mueble.getPrecioBase();
        Integer aumentoVariante = (variante != null) ? variante.getAumentoPrecio() : 0;

        return (precioBase + aumentoVariante) * cantidad;
    }
}
