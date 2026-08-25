package com.coffee.hexagonal.application.ports.in;

import com.coffee.hexagonal.domain.model.BrewMethod;
import com.coffee.hexagonal.domain.model.Order;

/**
 * PUERTO DE ENTRADA (Primary Port): Esta interfaz define el contrato que los adaptadores primarios
 * utilizan para interactuar con el caso de uso de procesar un pedido de café.
 */
public interface ProcessCoffeeOrderUseCase {

    /**
     * Procesa un pedido de café: verifica si hay suficiente inventario del tipo de grano solicitado.
     * Si el stock es suficiente, lo descuenta, crea un pedido confirmado y lo persiste.
     * Si no hay suficiente inventario, lanza una excepción de inventario insuficiente.
     */
    Order processOrder(String coffeeBeanType, int quantityGrams, BrewMethod brewMethod);
}
