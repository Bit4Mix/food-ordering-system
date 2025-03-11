package com.ibabrou.food_ordering_system.config;

import com.ibabrou.food_ordering_system.controller.OrderController;
import com.ibabrou.food_ordering_system.runner.CliRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfig {

    @Bean
    @Primary  // So that this bean replaces the main CliRunner bean in tests
    public CliRunner cliRunner(OrderController orderController) {
        // Override the run method so it doesn't do the actual logic
        return new CliRunner(orderController) {
            @Override
            public void run(String... args) {
                // Instead of doing real logic, we do nothing.
                System.out.println("Test mode: run method is mocked.");
            }
        };
    }
}
