package com.coffee.hexagonal.application.ports.out;

import com.coffee.hexagonal.domain.model.CoffeeBean;

import java.util.Optional;

/**
 * PUERTO DE SALIDA (Secondary Port): Esta interfaz define cómo el caso de uso interactúa con el
 * inventario de granos de café.
 */
public interface InventoryPort {
    Optional<CoffeeBean> findByType(String beanType);
    void updateStock(CoffeeBean coffeeBean);
}
