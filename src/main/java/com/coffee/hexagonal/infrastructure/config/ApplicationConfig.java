package com.coffee.hexagonal.infrastructure.config;

import com.coffee.hexagonal.application.ports.in.ProcessCoffeeOrderUseCase;
import com.coffee.hexagonal.application.ports.out.InventoryPort;
import com.coffee.hexagonal.application.ports.out.OrderRepositoryPort;
import com.coffee.hexagonal.application.usecases.ProcessCoffeeOrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ProcessCoffeeOrderUseCase processCoffeeOrderUseCase(InventoryPort inventoryPort,
                                                                 OrderRepositoryPort orderRepositoryPort) {
        return new ProcessCoffeeOrderService(inventoryPort, orderRepositoryPort);
    }
}
