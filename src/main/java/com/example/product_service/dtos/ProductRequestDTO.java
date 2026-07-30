package com.example.product_service.dtos;

import lombok.Getter;
import lombok.Setter;
import com.example.product_service.model.ProductType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductRequestDTO {

        private Long clienteId;
        private ProductType tipo;
        private String nombre;
        private String descripcion;
        private BigDecimal montoAsociado;
        private BigDecimal tasaInteres;
        private LocalDate fechaInicio;
        private LocalDate fechaVencimiento;

}
