package com.example.product_service.dtos;

import lombok.Getter;
import lombok.Setter;
import com.example.product_service.model.ProductType;

@Setter
@Getter
public class ProductDTO {
    private Long id;
    private Long clienteId;
    private ProductType tipo;
    private String nombre;
    private String descripcion;
    private boolean activo;

}
