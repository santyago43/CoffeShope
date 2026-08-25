package com.coffee.hexagonal.domain.model;

import com.coffee.hexagonal.domain.exception.BusinessException;
import com.coffee.hexagonal.domain.exception.InsufficientInventoryException;

/**
 * ENTIDAD DE DOMINIO: representa un tipo de grano de café (por ejemplo, Geisha,
 * Bourbon Rosado) y la cantidad disponible en inventario, expresada en gramos.
 */
public record CoffeeBean(String type, String origin, int availableStockGrams) {

    public CoffeeBean {
        if (type == null || type.isBlank()) {
            throw new BusinessException("El tipo de grano no puede estar vacio");
        }
        if (availableStockGrams < 0) {
            throw new BusinessException("El stock disponible no puede ser negativo");
        }
    }

    public boolean hasEnoughStock(int requestedGrams) {
        return this.availableStockGrams >= requestedGrams;
    }

    /**
     * Devuelve una nueva instancia de CoffeeBean con el stock reducido por la
     * cantidad solicitada (en gramos). Si la cantidad es menor o igual a cero,
     * lanza una BusinessException. Si no hay suficiente stock, lanza una
     * InsufficientInventoryException.
     */
    public CoffeeBean deductStock(int requestedGrams) {
        if (requestedGrams <= 0) {
            throw new BusinessException("La cantidad solicitada debe ser mayor a cero");
        }
        if (!hasEnoughStock(requestedGrams)) {
            throw new InsufficientInventoryException(type, requestedGrams, availableStockGrams);
        }
        return new CoffeeBean(type, origin, availableStockGrams - requestedGrams);
    }
}
