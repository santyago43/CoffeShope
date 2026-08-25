package com.coffee.hexagonal.infrastructure.adapters.out.persistence;

import com.coffee.hexagonal.application.ports.out.OrderRepositoryPort;
import com.coffee.hexagonal.domain.model.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ADAPTADOR SECUNDARIO: implementación en memoria del puerto OrderRepositoryPort.
 * Simula una base de datos de pedidos usando un mapa en memoria.
 */
@Component
public class InMemoryOrderAdapter implements OrderRepositoryPort {

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Order save(Order order) {
        Long id = order.id() != null ? order.id() : idGenerator.getAndIncrement();
        Order persistedOrder = new Order(id, order.coffeeBeanType(), order.quantityGrams(), order.brewMethod(), order.status());
        orders.put(id, persistedOrder);
        return persistedOrder;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }
}
