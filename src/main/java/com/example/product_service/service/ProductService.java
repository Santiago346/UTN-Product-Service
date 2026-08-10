package com.example.product_service.service;

import com.example.product_service.dtos.ProductDTO;
import com.example.product_service.dtos.ProductRequestDTO;
import com.example.product_service.exceptions.ProductNotFoundException;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.model.Product;
import com.example.product_service.model.ProductType;
import org.springframework.stereotype.Service;
import com.example.product_service.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;


    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<ProductDTO> getProductos() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    public ProductDTO getProductoById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id)
                );

        return mapper.toResponse(product);
    }


    public List<ProductDTO> getProductosByCliente(Long clienteId) {

        return repository.findByClienteId(clienteId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    public List<ProductDTO> getProductosByTipo(ProductType tipo) {

        return repository.findByTipo(tipo)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    public List<ProductDTO> getProductosActivos() {

        return repository.findByActivo(true)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    public ProductDTO addProducto(ProductRequestDTO request) {

        Product product = mapper.toEntity(request);

        Product savedProduct = repository.save(product);

        return mapper.toResponse(savedProduct);
    }


    public ProductDTO updateProducto(Long id, ProductRequestDTO request) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setTipo(request.getTipo());
        product.setNombre(request.getNombre());
        product.setDescripcion(request.getDescripcion());
        product.setMontoAsociado(request.getMontoAsociado());
        product.setTasaInteres(request.getTasaInteres());
        product.setActivo(request.isActivo());
        product.setFechaInicio(request.getFechaInicio());
        product.setFechaVencimiento(request.getFechaVencimiento());

        Product updatedProduct = repository.save(product);

        return mapper.toResponse(updatedProduct);
    }


    public void deleteById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id)
                );

        repository.delete(product);
    }

}