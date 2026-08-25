package com.coffee.hexagonal.domain.exception;

/**
 * Excepción de dominio que indica que no hay suficiente inventario de un
 * tipo de grano de café para cumplir con la cantidad solicitada en un
 * pedido. 
 *
 * El constructor recibe el tipo de grano, la cantidad solicitada y la
 * cantidad disponible, y construye un mensaje descriptivo que puede mostrarse
 * al usuario o registrar en logs.
 */
public class InsufficientInventoryException extends BusinessException {

    public InsufficientInventoryException(String beanType, int requestedGrams, int availableGrams) {
        super("Inventario insuficiente para el grano '" + beanType + "'. Solicitado: "
                + requestedGrams + "g, Disponible: " + availableGrams + "g");
    }
}
