package com.coffee.hexagonal.infrastructure.adapters.in.rest;

import com.coffee.hexagonal.application.ports.in.ProcessCoffeeOrderUseCase;
import com.coffee.hexagonal.domain.model.BrewMethod;
import com.coffee.hexagonal.domain.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * ADAPTADOR PRIMARIO (Puerto de Entrada): controlador REST que traduce
 * peticiones HTTP en llamadas al caso de uso. Solo depende de la interfaz
 * ProcessCoffeeOrderUseCase, nunca de su implementación concreta.
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final ProcessCoffeeOrderUseCase useCase;

    public OrderController(ProcessCoffeeOrderUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody CreateOrderRequest request) {
        BrewMethod brewMethod = BrewMethod.valueOf(request.brewMethod().toUpperCase());
        return useCase.processOrder(request.coffeeBeanType(), request.quantityGrams(), brewMethod);
    }
}
