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

        Product producto = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Producto no encontrado con id: " + id
                        )
                );

        return mapper.toResponse(producto);
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

        Product producto = mapper.toEntity(request);

        Product guardado = repository.save(producto);

        return mapper.toResponse(guardado);
    }


    public ProductDTO updateProducto(Long id, ProductRequestDTO request) {

        Product producto = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Producto no encontrado con id: " + id
                        )
                );


        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setMontoAsociado(request.getMontoAsociado());
        producto.setTasaInteres(request.getTasaInteres());


        Product actualizado = repository.save(producto);

        return mapper.toResponse(actualizado);
    }


    public void deleteById(Long id) {

        Product producto = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Producto no encontrado con id: " + id
                        )
                );

        repository.delete(producto);
    }

}