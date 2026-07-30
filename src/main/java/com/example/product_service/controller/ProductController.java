package com.example.product_service.controller;

import com.example.product_service.dtos.ProductDTO;
import com.example.product_service.dtos.ProductRequestDTO;
import com.example.product_service.model.ProductType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.product_service.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }


    @GetMapping
    public List<ProductDTO> getProductos() {
        return service.getProductos();
    }


    @GetMapping("/{id}")
    public ProductDTO getProductoById(
            @PathVariable Long id
    ) {
        return service.getProductoById(id);
    }


    @GetMapping("/cliente/{clienteId}")
    public List<ProductDTO> getProductosByCliente(
            @PathVariable Long clienteId
    ) {
        return service.getProductosByCliente(clienteId);
    }


    @GetMapping("/tipo/{tipo}")
    public List<ProductDTO> getProductosByTipo(
            @PathVariable ProductType tipo
    ) {
        return service.getProductosByTipo(tipo);
    }


    @GetMapping("/activos")
    public List<ProductDTO> getProductosActivos() {
        return service.getProductosActivos();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProducto(
            @RequestBody ProductRequestDTO request
    ) {
        return service.addProducto(request);
    }


    @PutMapping("/{id}")
    public ProductDTO updateProducto(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO request
    ) {
        return service.updateProducto(id, request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducto(
            @PathVariable Long id
    ) {
        service.deleteById(id);
    }
}
