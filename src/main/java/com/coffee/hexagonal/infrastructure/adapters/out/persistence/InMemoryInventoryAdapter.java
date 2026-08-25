package com.coffee.hexagonal.infrastructure.adapters.out.persistence;

import com.coffee.hexagonal.application.ports.out.InventoryPort;
import com.coffee.hexagonal.domain.model.CoffeeBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ADAPTADOR SECUNDARIO: implementación en memoria del puerto InventoryPort.
 * Simula una base de datos de inventario usando un mapa en memoria, precargado
 * con algunos granos de café de especialidad para poder probar el flujo.
 */
@Component
public class InMemoryInventoryAdapter implements InventoryPort {

    private final Map<String, CoffeeBean> inventory = new ConcurrentHashMap<>();

    public InMemoryInventoryAdapter() {
        seedInventory();
    }

    private void seedInventory() {
        save(new CoffeeBean("Geisha", "Panama - Boquete", 2000));
        save(new CoffeeBean("Bourbon Rosado", "Colombia - Huila", 1500));
        save(new CoffeeBean("Caturra", "Colombia - Narino", 5000));
        save(new CoffeeBean("Pacamara", "El Salvador", 500));
    }

    private void save(CoffeeBean coffeeBean) {
        inventory.put(coffeeBean.type(), coffeeBean);
    }

    @Override
    public Optional<CoffeeBean> findByType(String beanType) {
        return Optional.ofNullable(inventory.get(beanType));
    }

    @Override
    public void updateStock(CoffeeBean coffeeBean) {
        inventory.put(coffeeBean.type(), coffeeBean);
    }
}
