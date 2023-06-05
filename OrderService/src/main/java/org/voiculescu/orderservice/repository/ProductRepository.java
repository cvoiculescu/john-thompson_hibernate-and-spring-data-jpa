package org.voiculescu.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.orderservice.entity.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescription(String description);

}
