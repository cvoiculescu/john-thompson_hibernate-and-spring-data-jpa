package org.voiculescu.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.orderservice.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByDescription(String description);

}
