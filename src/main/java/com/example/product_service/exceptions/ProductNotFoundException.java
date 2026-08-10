package com.example.product_service.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}
