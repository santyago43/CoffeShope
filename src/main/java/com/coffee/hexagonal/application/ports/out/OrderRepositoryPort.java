package com.coffee.hexagonal.application.ports.out;

import com.coffee.hexagonal.domain.model.Order;

import java.util.Optional;

/**
 * PUERTO DE SALIDA (Secondary Port): Esta interfaz define cómo el caso de uso persiste y
 * recupera pedidos de café.
 */
public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
}
