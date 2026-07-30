package com.example.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;

    @Enumerated(EnumType.STRING)
    private ProductType tipo;

    private String nombre;
    private String descripcion;

    private BigDecimal montoAsociado;
    private BigDecimal tasaInteres;

    private boolean activo;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private LocalDateTime fechaAlta;

    public boolean estaVigente() {
        if (fechaVencimiento == null) return activo;
        return activo && !LocalDate.now().isAfter(fechaVencimiento);
    }
}
