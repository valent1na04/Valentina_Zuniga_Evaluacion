package cl.ubiobio.tareita.strategy;

import cl.ubiobio.tareita.models.Mueble;
import cl.ubiobio.tareita.models.Variante;
import org.springframework.stereotype.Component;

/**
 * Strategy Pattern: Premium price calculation
 * Applies a premium markup for high-end materials
 * Materials like "Cuero", "Roble", "Mármol" get 20% premium
 */
@Component
public class PremiumPriceStrategy implements PriceCalculationStrategy {

    private static final String[] PREMIUM_MATERIALS = {"Cuero", "Roble", "Mármol", "Caoba"};
    private static final double PREMIUM_MARKUP = 1.20;

    @Override
    public Integer calculatePrice(Mueble mueble, Variante variante, Integer cantidad) {
        if (mueble == null || cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("Mueble y cantidad son requeridos");
        }

        Integer precioBase = mueble.getPrecioBase();
        Integer aumentoVariante = (variante != null) ? variante.getAumentoPrecio() : 0;
        Integer precioUnitario = precioBase + aumentoVariante;

        // Apply premium markup if material is premium
        if (isPremiumMaterial(mueble.getMaterial())) {
            precioUnitario = (int) (precioUnitario * PREMIUM_MARKUP);
        }

        return precioUnitario * cantidad;
    }

    private boolean isPremiumMaterial(String material) {
        if (material == null) return false;

        for (String premiumMaterial : PREMIUM_MATERIALS) {
            if (material.toLowerCase().contains(premiumMaterial.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
