package com.coffee.hexagonal.domain.model;

import com.coffee.hexagonal.domain.exception.BusinessException;

/**
 * ENTIDAD DE DOMINIO: representa un pedido de café realizado por un cliente.
 * Un pedido especifica qué tipo de grano se desea, la cantidad en gramos y el
 * método de preparación sugerido (por ejemplo, V60, espresso, etc.).
 */
public record Order(Long id, String coffeeBeanType, int quantityGrams, BrewMethod brewMethod, OrderStatus status) {

    public Order {
        if (coffeeBeanType == null || coffeeBeanType.isBlank()) {
            throw new BusinessException("El tipo de grano del pedido no puede estar vacio");
        }
        if (quantityGrams <= 0) {
            throw new BusinessException("La cantidad solicitada debe ser mayor a cero gramos");
        }
        if (brewMethod == null) {
            throw new BusinessException("Debe especificar un metodo de preparacion sugerido");
        }
        if (status == null) {
            throw new BusinessException("El pedido debe tener un estado");
        }
    }

    /**
     * Fabrica un nuevo pedido en estado CONFIRMED con el mismo id.
     * Debido a la inmutabilidad del record, no modificamos la instancia actual;
     * en su lugar, devolvemos una nueva instancia con el estado cambiado.
     */
    public Order confirm() {
        return new Order(id, coffeeBeanType, quantityGrams, brewMethod, OrderStatus.CONFIRMED);
    }
}
