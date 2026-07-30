package com.example.product_service.repository;

import com.example.product_service.model.Product;
import com.example.product_service.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByClienteId(Long clienteId);

    List<Product> findByTipo(ProductType tipo);

    List<Product> findByActivo(boolean activo);
}
