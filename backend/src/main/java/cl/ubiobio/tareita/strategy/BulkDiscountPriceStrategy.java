package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.springframework.stereotype.Component;

/**
 * Strategy Pattern: Bulk discount price calculation
 * Applies discount for large quantities:
 * - 5% discount for 5-9 items
 * - 10% discount for 10-19 items
 * - 15% discount for 20+ items
 */
@Component
public class BulkDiscountPriceStrategy implements PriceCalculationStrategy {

    @Override
    public Integer calculatePrice(Mueble mueble, Variante variante, Integer cantidad) {
        if (mueble == null || cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("Mueble y cantidad son requeridos");
        }

        Integer precioBase = mueble.getPrecioBase();
        Integer aumentoVariante = (variante != null) ? variante.getAumentoPrecio() : 0;
        Integer precioUnitario = precioBase + aumentoVariante;
        Integer precioTotal = precioUnitario * cantidad;

        // Apply bulk discount
        if (cantidad >= 20) {
            precioTotal = (int) (precioTotal * 0.85); // 15% discount
        } else if (cantidad >= 10) {
            precioTotal = (int) (precioTotal * 0.90); // 10% discount
        } else if (cantidad >= 5) {
            precioTotal = (int) (precioTotal * 0.95); // 5% discount
        }

        return precioTotal;
    }
}
