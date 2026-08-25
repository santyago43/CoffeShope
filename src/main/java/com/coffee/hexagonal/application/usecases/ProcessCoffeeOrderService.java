package com.coffee.hexagonal.application.usecases;

import com.coffee.hexagonal.application.ports.in.ProcessCoffeeOrderUseCase;
import com.coffee.hexagonal.application.ports.out.InventoryPort;
import com.coffee.hexagonal.application.ports.out.OrderRepositoryPort;
import com.coffee.hexagonal.domain.exception.BusinessException;
import com.coffee.hexagonal.domain.model.BrewMethod;
import com.coffee.hexagonal.domain.model.CoffeeBean;
import com.coffee.hexagonal.domain.model.Order;
import com.coffee.hexagonal.domain.model.OrderStatus;

/**
 * CASO DE USO (Application Service): Implementa la lógica de negocio para procesar un pedido de
 * café. Esta clase orquesta la interacción entre el dominio y los puertos de salida.
 */
public class ProcessCoffeeOrderService implements ProcessCoffeeOrderUseCase {

    private final InventoryPort inventoryPort;
    private final OrderRepositoryPort orderRepositoryPort;

    public ProcessCoffeeOrderService(InventoryPort inventoryPort, OrderRepositoryPort orderRepositoryPort) {
        this.inventoryPort = inventoryPort;
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order processOrder(String coffeeBeanType, int quantityGrams, BrewMethod brewMethod) {
        // Paso 1: Obtener el grano de café solicitado desde el inventario.
        // Si no se encuentra, lanzamos una excepción de negocio indicando que el grano no existe.
        CoffeeBean coffeeBean = inventoryPort.findByType(coffeeBeanType)
                .orElseThrow(() -> new BusinessException("El grano '" + coffeeBeanType + "' no existe en el inventario"));

        // Paso 2: Verificar si hay suficiente stock y, si es así, descontar la cantidad requerida.
        // El método deductStock lanza InsufficientInventoryException si el stock es insuficiente.
        CoffeeBean updatedCoffeeBean = coffeeBean.deductStock(quantityGrams);
        inventoryPort.updateStock(updatedCoffeeBean);

        // Paso 3: Crear un nuevo objeto de pedido con los datos proporcionados y estado CONFIRMED.
        // El constructor de Order valida las invariantes del dominio (por ejemplo, que la cantidad sea positiva).
        Order order = new Order(null, coffeeBeanType, quantityGrams, brewMethod, OrderStatus.CONFIRMED);

        // Paso 4: Guardar el pedido recién creado mediante el repositorio de pedidos (puerto de salida).
        // La implementación concreta del repositorio se encargará de la persistencia (en memoria, BD, etc.).
        return orderRepositoryPort.save(order);
    }
}
