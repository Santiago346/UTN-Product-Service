package com.example.product_service.config;

import com.example.product_service.model.Product;
import com.example.product_service.model.ProductType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.product_service.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {

            repository.save(new Product(
                    null,
                    1L,
                    ProductType.CUENTA_CORRIENTE,
                    "Cuenta Corriente Premium",
                    "Cuenta corriente con beneficios exclusivos",
                    new BigDecimal("250000"),
                    BigDecimal.ZERO,
                    true,
                    LocalDate.of(2026, 1, 10),
                    null,
                    LocalDateTime.now()
            ));


            repository.save(new Product(
                    null,
                    1L,
                    ProductType.SEGURO_VIDA,
                    "Seguro de Vida Familiar",
                    "Cobertura ante fallecimiento e invalidez",
                    new BigDecimal("10000000"),
                    BigDecimal.ZERO,
                    true,
                    LocalDate.of(2026, 2, 1),
                    LocalDate.of(2027, 2, 1),
                    LocalDateTime.now()
            ));


            repository.save(new Product(
                    null,
                    2L,
                    ProductType.PLAZO_FIJO,
                    "Plazo Fijo 90 días",
                    "Inversión con tasa fija",
                    new BigDecimal("500000"),
                    new BigDecimal("32.5"),
                    true,
                    LocalDate.of(2026, 3, 1),
                    LocalDate.of(2026, 5, 30),
                    LocalDateTime.now()
            ));

        };
    }
}
