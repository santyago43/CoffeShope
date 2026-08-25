package com.coffee.hexagonal.infrastructure.adapters.in.rest;

/**
 * Objeto de transferencia de datos (DTO) que representa la entrada esperada
 * por el endpoint REST para crear un pedido de café. Este registro pertenece
 * exclusivamente a la capa de infraestructura (adaptador primario REST); el
 * dominio y los casos de uso no conocen esta clase, sino que trabajan con
 * el modelo de dominio (Order) y el puerto de entrada (ProcessCoffeeOrderUseCase).
 */
public record CreateOrderRequest(String coffeeBeanType, int quantityGrams, String brewMethod) {
}
