package com.example.product_service.mapper;

import com.example.product_service.dtos.ProductDTO;
import com.example.product_service.dtos.ProductRequestDTO;
import com.example.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toResponse(Product product) {

        System.out.println(product.getNombre());
        ProductDTO response = new ProductDTO();

        response.setId(product.getId());
        response.setClienteId(product.getClienteId());
        response.setTipo(product.getTipo());
        response.setNombre(product.getNombre());
        response.setDescripcion(product.getDescripcion());
        response.setActivo(product.isActivo());

        return response;
    }


    public Product toEntity(ProductRequestDTO request) {

        Product product = new Product();

        product.setClienteId(request.getClienteId());
        product.setTipo(request.getTipo());
        product.setNombre(request.getNombre());
        product.setDescripcion(request.getDescripcion());
        product.setMontoAsociado(request.getMontoAsociado());
        product.setTasaInteres(request.getTasaInteres());
        product.setFechaInicio(request.getFechaInicio());
        product.setFechaVencimiento(request.getFechaVencimiento());
        product.setActivo(true);

        return product;
    }
}
