package org.voiculescu.orderservice.services;

import org.voiculescu.orderservice.entity.Product;

public interface ProductService {
    Product save(Product product);
    Product updateQOH(Long id, Integer quantityOnHand);
}
